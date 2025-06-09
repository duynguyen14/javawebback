package com.example.back.service;

import com.example.back.dto.response.Bill.BillDTO;
import com.example.back.entity.Bill;
import com.example.back.mapper.BillMapper;
import com.example.back.repository.BillRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;

    @Transactional(readOnly = true)
    public List<BillDTO> getAllBills() {
        List<Bill> bills = billRepository.findAll();

        bills.forEach(bill -> {
            bill.getBillDetails().size();
            if (bill.getUser() != null) bill.getUser().getUserName();
            if (bill.getAddress() != null) bill.getAddress().getCity();
        });

        return bills.stream()
                .map(BillMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BillDTO getBillById(Integer id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bill not found"));

        bill.getBillDetails().size();
        if (bill.getUser() != null) bill.getUser().getUserName();
        if (bill.getAddress() != null) bill.getAddress().getCity();

        return BillMapper.toDTO(bill);
    }

    @Transactional
    public void updateBillStatus(Integer id, String newStatus) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bill not found"));
        bill.setStatus(newStatus);
        billRepository.save(bill);
    }

}
