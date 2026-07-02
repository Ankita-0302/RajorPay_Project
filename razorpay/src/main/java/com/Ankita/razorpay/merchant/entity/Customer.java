package com.Ankita.razorpay.merchant.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="merchant_id",nullable=false)
    private Merchant merchant;

    @Column(length = 200)
    private String name;

    @Column(length =200)
    private String email; //we can't make it unique because many customer can login to many merchant website if we make it unique then it will be not possible for a customer to login to different merchant(zara,HM) portable with same email id

    @Column(length = 20)
    private String contactNumber;

    private LocalDateTime deletedAt;


}
