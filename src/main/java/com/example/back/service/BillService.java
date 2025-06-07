package com.example.back.service;

import com.example.back.dto.request.Bill.CreateBillRequest;
import com.example.back.dto.response.Bill.BillDetailResponse;
import com.example.back.dto.response.Bill.BillResponse;
import com.example.back.dto.response.Cart.CartResponse;
import com.example.back.dto.response.Product.ProductSizeDTO;
import com.example.back.entity.*;
import com.example.back.enums.BillStatus;
import com.example.back.enums.ErrorCodes;
import com.example.back.exception.AppException;
import com.example.back.mapper.AddressMapper;
import com.example.back.mapper.BillMapper;
import com.example.back.repository.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BillService {
    BillDetailRepository billDetailRepository;
    BillRepository billRepository;
    UserRepository userRepository;
    ProductSizeRepository productSizeRepository;
    BillMapper billMapper;
    ShoppingCartRepository shoppingCartRepository;
    AddressRepository addressRepository;
    ProductRepository productRepository;
    AddressMapper addressMapper;
    User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new AppException(ErrorCodes.USER_NOT_FOUND));
    }
    public List<BillResponse> getAllBillByUser(){
        User user = getCurrentUser();
        List<Bill> bills=billRepository.findByUser(user);
        return bills.stream().map(billMapper::toBillResponse).toList();
    }
    @Transactional
    public BillResponse createBill(CreateBillRequest billRequest){
        User user =getCurrentUser();
        Address address =addressRepository.findById(billRequest.getAddressId()).orElseThrow(()-> new AppException(ErrorCodes.ADDRESS_NOT_FOUND));
        Bill bill = Bill.builder()
                .user(user)
                .time(LocalDateTime.now())
                .status(BillStatus.PENDING.getLabel())
                .address(address)

                .build();
        bill = billRepository.save(bill); // lưu để có ID
        BigDecimal total = BigDecimal.ZERO;
        for (ProductSizeDTO productSizeDTO : billRequest.getProductSizeDTOs()) {
            ProductSize productSize = productSizeRepository.findByIdWithProduct(productSizeDTO.getProductSizeId())
                    .orElseThrow(() -> new AppException(ErrorCodes.PRODUCT_NOT_FOUND));
            if(productSize.getQuantity()< productSizeDTO.getQuantity()){
                throw new AppException(ErrorCodes.PRODUCT_QUANTITY_UNAVAILABLE);
            }
            Product product =productSize.getProduct();
            BigDecimal quantity =BigDecimal.valueOf(productSizeDTO.getQuantity());
            total =total.add(product.getPrice().multiply(quantity));
            BillDetail billDetail = BillDetail.builder()
                    .bill(bill) // liên kết bill
                    .productSize(productSize)
                    .quantity(productSizeDTO.getQuantity())
                    .build();
            billDetail.setId(new BillDetailId(bill.getBillId(), productSize.getId()));
            productSize.setQuantity(productSize.getQuantity()-billDetail.getQuantity());
            productSizeRepository.save(productSize);
            billDetailRepository.save(billDetail);
        }
        bill.setTotal(total);
        billRepository.save(bill);
        ShoppingCart shoppingCart=shoppingCartRepository.findByUser(user).orElseThrow(()-> new AppException(ErrorCodes.SHOPPING_CART_NOT_FOUND));
        shoppingCart.getCartDetails().clear();
        shoppingCartRepository.delete(shoppingCart);
//        shoppingCartRepository.flush();
        return billMapper.toBillResponse(bill);
    }
    public BillDetailResponse getBillDetail(Integer id){
        User user =getCurrentUser();
        Bill bill =billRepository.getBillDetailById(id).orElseThrow(()-> new AppException(ErrorCodes.BILL_NOT_FOUND));
        Address address =bill.getAddress();
        Set<BillDetail> billDetails =bill.getBillDetails();
        List<CartResponse> products=billDetails.stream().map(billDetail -> {
            ProductSize productSize =billDetail.getProductSize();
            Product product =productSize.getProduct();
            Size size =productSize.getSize();
            return CartResponse.builder()
                    .price(product.getPrice())
                    .images(product.getImages().stream().map(Image::getImage).collect(Collectors.toSet()))
                    .productName(product.getName())
                    .sizeName(size.getSizeName())
                    .productSizeId(productSize.getId())
                    .quantity(billDetail.getQuantity())
                    .build();
        }).toList();
        return BillDetailResponse.builder()
                .address(addressMapper.toAddressResponse(address))
                .bill(billMapper.toBillResponse(bill))
                .products(products)
                .build();
    }
    @Transactional
    public String cancelBill(Integer id){
        User user =getCurrentUser();
        Bill bill =billRepository.getBillDetailById(id).orElseThrow(()-> new AppException(ErrorCodes.BILL_NOT_FOUND));
        if(!bill.getUser().equals(user)){
            throw new AppException(ErrorCodes.UNAUTHORIZED);
        }
        if(!bill.getStatus().equalsIgnoreCase("chờ xác nhận")){
            throw new AppException(ErrorCodes.INVALID_BILL_STATUS);
        }
        Set<BillDetail> billDetails =bill.getBillDetails();
        for(BillDetail billDetail: billDetails){
            ProductSize productSize =billDetail.getProductSize();
            productSize.setQuantity(productSize.getQuantity()+billDetail.getQuantity());
            productSizeRepository.save(productSize);
        }
        bill.setStatus(BillStatus.CANCELLED.getLabel());
        billRepository.save(bill);
        return "huỷ đơn hàng thành công";
    }
}
