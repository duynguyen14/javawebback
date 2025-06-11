package com.example.back.service;

import com.example.back.dto.response.Bill.BillResponse;
import com.example.back.dto.response.Bill.RecentBillDTO;
import com.example.back.dto.response.Bill.RevenueAndTotalRespone;
import com.example.back.entity.Bill;
import com.example.back.entity.User;
import com.example.back.enums.ErrorCodes;
import com.example.back.exception.AppException;
import com.example.back.mapper.BillMapper;
import com.example.back.repository.BillDetailRepository;
import com.example.back.repository.BillRepository;
import com.example.back.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BillService {
    BillDetailRepository billDetailRepository;
    BillRepository billRepository;

    UserRepository userRepository;
    BillMapper billMapper;

    public List<BillResponse> getAllBillByUser(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =userRepository.findByUserName(userName).orElseThrow(()-> new AppException(ErrorCodes.USER_NOT_FOUND));
        List<Bill> bills=billRepository.getByUser(user);
        return bills.stream().map(billMapper::toBillResponse).toList();
    }
    public RevenueAndTotalRespone getBillStats() {
        Long totalOrders = billRepository.countCompletedBills();
        BigDecimal totalRevenue = billDetailRepository.sumTotalRevenueFromCompletedBills();

        return RevenueAndTotalRespone.builder()
                .totalOrder(totalOrders)
                .totalRevenue(totalRevenue)
                .build();
    }
    //

    public List<RecentBillDTO> getMostRecentBill(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Bill> recentBills = billRepository.findMostRecentBill(pageable);
        if (recentBills.isEmpty()) {
            throw new RuntimeException("Không tìm thấy đơn hàng nào");
        }

        return recentBills.stream()
                .map(bill -> RecentBillDTO.builder()
                        .billId(bill.getBillId())
                        .customerName(bill.getUser().getUserName())
                        .orderDate(bill.getTime())
                        .orderValue(bill.getTotal())
                        .orderStatus(bill.getStatus())
                        .build())
                .collect(Collectors.toList());
    }



}
