package com.example.back.controllers;

import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.Bill.BillResponse;
import com.example.back.service.BillService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
