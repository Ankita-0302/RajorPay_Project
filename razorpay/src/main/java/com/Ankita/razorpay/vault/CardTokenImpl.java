package com.Ankita.razorpay.vault;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name= "card_token")
public class CardTokenImpl extends CardToken
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length=50)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "vault_card_" + "id" , nullable= false)
    private VaultCard vaultCard;

    @Column(nullable = false)
    private UUID customer;

    @Column(nullable = false)
    private UUID merchant;

    private UUID revokedAt;


}
