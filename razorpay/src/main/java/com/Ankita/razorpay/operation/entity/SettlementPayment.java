package com.Ankita.razorpay.operation.entity;

import jakarta.persistence.*;

public class SettlementPayment {

    @EmbeddedId
    private SettlementPaymentId id;

    @MapsId("SettlementId")
    @ManyToOne(fetch= FetchType.LAZY,optional=false)
    @JoinColumn(name="settlement_id",nullable = false)
    private Settlement settlement;


}
