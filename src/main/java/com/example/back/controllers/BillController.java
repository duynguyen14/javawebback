package com.example.back.controllers;

import com.example.back.dto.request.Bill.CreateBillRequest;
import com.example.back.dto.request.Bill.PaymentRequest;
import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.Bill.BillDetailResponse;
import com.example.back.dto.response.Bill.BillResponse;
import com.example.back.dto.response.Bill.PaymentResponse;
import com.example.back.service.BillService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("${api.key}/bill/")
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


    @PostMapping("create")
    public APIResponse<BillResponse> createBill(@RequestBody CreateBillRequest createBillRequest){
        return APIResponse.<BillResponse>builder()
                .result(billService.createBill(createBillRequest))
                .build();
    }
    @GetMapping("detail/{id}")
    public APIResponse<BillDetailResponse> getBillDetail(@PathVariable Integer id){
        return APIResponse.<BillDetailResponse>builder()
                .result(billService.getBillDetail(id))
                .build();
    }
    @PatchMapping("detail/{id}")
    public APIResponse<String> cancelBill(@PathVariable Integer id){
        return APIResponse.<String>builder()
                .result(billService.cancelBill(id))
                .build();
    }
    @PostMapping("create/payment")
    public APIResponse<PaymentResponse> createPaymentResponse(@RequestBody PaymentRequest paymentRequest, HttpServletRequest httpServletRequest){
        return APIResponse.<PaymentResponse>builder()
                .result(billService.createPayment(paymentRequest,httpServletRequest))
                .build();
    }
}
