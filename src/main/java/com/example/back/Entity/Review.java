package com.example.back.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "REVIEW")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewId")
    private Integer reviewId;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "Rating")
    private Integer rating;

    @Column(name = "Time")
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id")
    private User user;
}
