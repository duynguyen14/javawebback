package com.example.back.mapper;

import com.example.back.dto.response.Bill.BillDTO;
import com.example.back.dto.response.Bill.BillDetailDTO;
import com.example.back.entity.Address;
import com.example.back.entity.Bill;
import com.example.back.entity.BillDetail;

import java.util.List;
import java.util.stream.Collectors;

public class BillMapper {

    public static BillDTO toDTO(Bill bill) {
        return BillDTO.builder()
                .billId(bill.getBillId())
                .time(bill.getTime())
                .status(bill.getStatus())
                .userName(bill.getUser() != null ? bill.getUser().getUserName() : "N/A")
                .address(buildAddressString(bill))
                .billDetails(mapBillDetails(bill.getBillDetails()))
                .build();
    }

    private static String buildAddressString(Bill bill) {
        Address address = bill.getAddress();
        if (address == null) return "N/A";

        return String.join(", ",
                safeValue(address.getStreet()),
                safeValue(address.getDetailedAddress()),
                safeValue(address.getCity())
        );
    }

    private static List<BillDetailDTO> mapBillDetails(List<BillDetail> billDetails) {
        return billDetails.stream().map(detail -> BillDetailDTO.builder()
                .productName(detail.getProduct() != null ? detail.getProduct().getName() : "N/A")
                .quantity(detail.getQuantity())
                .price(detail.getProduct() != null ? detail.getProduct().getPrice() : null)  // Lấy giá từ product
                .build()
        ).collect(Collectors.toList());
    }

    private static String safeValue(String value) {
        return value != null ? value : "N/A";
    }
}
