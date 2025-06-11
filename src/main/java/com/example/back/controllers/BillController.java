package com.example.back.controllers;

import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.Bill.BillResponse;
import com.example.back.dto.response.Bill.RecentBillDTO;
import com.example.back.dto.response.Bill.RevenueAndTotalRespone;
import com.example.back.service.BillService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.key}/bill")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class BillController {
    BillService billService;

    @GetMapping("getAll")
    public APIResponse<List<BillResponse>> getAllBillByUser(){
        return  APIResponse.<List<BillResponse>>builder()
                .result(billService.getAllBillByUser())
                .build();
    }
    //
    @GetMapping("stats")
    public RevenueAndTotalRespone getBillStats() {
        return billService.getBillStats();
    }
    @GetMapping("recent")
    public APIResponse<List<RecentBillDTO>> getMostRecentBills(@RequestParam(defaultValue = "5") int limit) {
        try {
            List<RecentBillDTO> recentBills = billService.getMostRecentBill(limit);
            return APIResponse.<List<RecentBillDTO>>builder()
                    .result(recentBills)
                    .build();
        } catch (RuntimeException e) {
            return APIResponse.<List<RecentBillDTO>>builder()
                    .code(404)
                    .message(e.getMessage())
                    .build();
        }
    }

}
