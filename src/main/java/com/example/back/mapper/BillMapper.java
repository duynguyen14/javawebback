package com.example.back.mapper;

import com.example.back.dto.response.Bill.BillResponse;
import com.example.back.entity.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillMapper {
    public BillResponse toBillResponse(Bill bill){
        return BillResponse.builder()
                .id(bill.getBillId())
                .total(bill.getTotal())
                .status(bill.getStatus())
                .time(bill.getTime())
                .build();
    }
}
