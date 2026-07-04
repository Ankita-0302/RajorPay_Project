package com.Ankita.razorpay.merchant.service.impl;

import com.Ankita.razorpay.common.exception.ResourceNotFoundException;
import com.Ankita.razorpay.merchant.Dto.request.CreateApiKeyRequest;
import com.Ankita.razorpay.merchant.Dto.resonse.ApiKeyCreateResponse;
import com.Ankita.razorpay.merchant.entity.ApiKey;
import com.Ankita.razorpay.merchant.entity.Merchant;
import com.Ankita.razorpay.merchant.repository.ApiKeyRepository;
import com.Ankita.razorpay.merchant.repository.MerchantRepository;
import com.Ankita.razorpay.merchant.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;

    @Override
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant=merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant",merchantId));


       String keyId="rzp_"+request.environment().name().toUpperCase()+"big_random_string";
       String rawSecret="big_random_secret";//TODO replace with crptographic random hex

       ApiKey apiKey= ApiKey.builder()
               .merchant(merchant)
               .keyId(keyId)
               .keySecretHash(rawSecret)//TODO:encode with BcryptPassword
               .build();

       apiKey=apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(),keyId,rawSecret,request.environment());
    }


}
