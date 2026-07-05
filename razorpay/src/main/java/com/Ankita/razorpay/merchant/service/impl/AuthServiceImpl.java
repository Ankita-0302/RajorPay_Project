package com.Ankita.razorpay.merchant.service.impl;

import com.Ankita.razorpay.common.enums.MerChantStatus;
import com.Ankita.razorpay.common.enums.UserRole;
import com.Ankita.razorpay.common.exception.DuplicateResourceException;
import com.Ankita.razorpay.merchant.Dto.request.MerchantSignupRequest;
import com.Ankita.razorpay.merchant.Dto.resonse.MerchantResponse;
import com.Ankita.razorpay.merchant.entity.AppUser;
import com.Ankita.razorpay.merchant.entity.Merchant;
import com.Ankita.razorpay.merchant.repository.AppUserRepository;
import com.Ankita.razorpay.merchant.repository.MerchantRepository;
import com.Ankita.razorpay.merchant.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;


    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest request) {
        if(merchantRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL","Merchant With email already exists:" + request.email());
        }
        Merchant merchant= Merchant.builder()
                .businessName(request.businessName())
                .businessType(request.businessType())
                .name(request.name())
                .email(request.email())
                .status(MerChantStatus.PENDING_KYC)
                .build();

        merchant =merchantRepository.save(merchant);

        AppUser appUser=AppUser.builder()
                        .email(request.email())
                        .merchant(merchant)
                        .passwordHash(request.password()) //TODO: encrypt using Bcrypt
                        .role(UserRole.OWNER)
                        .build();
                         appUserRepository.save(appUser);

        return new MerchantResponse(merchant.getId(),merchant.getName(),
                                    merchant.getEmail(),merchant.getBusinessName(),
                                    merchant.getBusinessType(),merchant.getStatus());
    }
}
