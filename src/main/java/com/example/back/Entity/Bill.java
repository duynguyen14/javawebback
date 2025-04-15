package com.example.back.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BILL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillId")
    private Integer billId;

    @Column(name = "Time")
    private LocalDateTime time;

    @Column(name = "Status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AddressId")
    private Address address;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BillDetail> billDetails = new ArrayList<>();
}
