package com.example.back.service;

import com.example.back.dto.response.Bill.BillResponse;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
