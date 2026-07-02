package com.Ankita.razorpay.merchant.service.impl;

import com.Ankita.razorpay.common.enums.MerChantStatus;
import com.Ankita.razorpay.merchant.Dto.request.MerchantSignupRequest;
import com.Ankita.razorpay.merchant.Dto.resonse.MerchantResponse;
import com.Ankita.razorpay.merchant.entity.Merchant;
import com.Ankita.razorpay.merchant.repository.AppUserRepository;
import com.Ankita.razorpay.merchant.repository.MerchantRepository;
import com.Ankita.razorpay.merchant.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;


    @Override
    public MerchantResponse signup(MerchantSignupRequest request) {
        if(merchantRepository.existsByEmail(request.email())){
            throw new RuntimeException("Merchant With email already exists:" + request.email());
        }
        Merchant merchant= Merchant.builder()
                .businessName(request.businessName())
                .businessType(request.businessType())
                .name(request.name())
                .email(request.email())
                .status(MerChantStatus.PENDING_KYC)
                .build();

        merchant =merchantRepository.save(merchant);

        return null;
    }
}
