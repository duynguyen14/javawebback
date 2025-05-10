package com.example.back.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BILL")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillId")
    Integer billId;

    @Column(name = "Time")
    LocalDateTime time;

    @Column(name = "Status")
    String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AddressId")
    Address address;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<BillDetail> billDetails = new ArrayList<>();
}
