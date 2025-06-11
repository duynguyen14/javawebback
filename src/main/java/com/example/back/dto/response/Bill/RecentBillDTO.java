package com.example.back.dto.response.Bill;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecentBillDTO {
    Integer billId;
    String customerName;
    LocalDateTime orderDate;
    Double orderValue;
    String orderStatus;
}

