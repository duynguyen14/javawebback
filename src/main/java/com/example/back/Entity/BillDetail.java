package com.example.back.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "BILLDETAIL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDetail {
    @EmbeddedId
    private BillDetailId id;

    @Column(name = "Quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("billId")
    @JoinColumn(name = "BillId")
    private Bill bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "ProductId")
    private Product product;
}
