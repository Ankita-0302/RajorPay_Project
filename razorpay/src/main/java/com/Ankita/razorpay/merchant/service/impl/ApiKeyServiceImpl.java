package com.Ankita.razorpay.merchant.service.impl;

import com.Ankita.razorpay.common.exception.ResourceNotFoundException;
import com.Ankita.razorpay.common.util.RandomizerUtil;
import com.Ankita.razorpay.merchant.Dto.request.CreateApiKeyRequest;
import com.Ankita.razorpay.merchant.Dto.resonse.ApiKeyCreateResponse;
import com.Ankita.razorpay.merchant.Dto.resonse.ApiKeyResponse;
import com.Ankita.razorpay.merchant.entity.ApiKey;
import com.Ankita.razorpay.merchant.entity.Merchant;
import com.Ankita.razorpay.merchant.repository.ApiKeyRepository;
import com.Ankita.razorpay.merchant.repository.MerchantRepository;
import com.Ankita.razorpay.merchant.service.ApiKeyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;

    @Override
    @Transactional
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant=merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant",merchantId));


       String keyId="rzp_"+request.environment().name().toLowerCase()+"_"+ RandomizerUtil.randomBase64(24);
       String rawSecret=RandomizerUtil.randomBase64(40);

       ApiKey apiKey= ApiKey.builder()
               .merchant(merchant)
               .keyId(keyId)
               .keySecretHash(rawSecret)//TODO:encode with BcryptPassword
               .environment(request.environment())
               .build();

       apiKey=apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(),keyId,rawSecret,request.environment());
    }

    @Override
    @Transactional
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        return apiKeyRepository.findByMerchant_Id(merchantId).stream()
                .map(apiKey
                        ->new ApiKeyResponse(
                        apiKey.getId(),
                        apiKey.getKeyId(),
                        apiKey.getEnvironment(),
                        apiKey.isEnabled(),
                        apiKey.getLastUsedAt(),
                        null))
                .toList();
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {
        ApiKey key=apiKeyRepository.findById(keyId)
                .filter(k-> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey",keyId));

        key.setEnabled(false);
        //apiKeyRepository.save(key); this line is not complusory for to add because we are using @Transactional for update it we do dirty commit
    }

    @Override
    @Transactional
    public ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey apiKey=apiKeyRepository.findById(keyId)
                .filter(k-> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey",keyId));

        String newRawSecret=RandomizerUtil.randomBase64(40);
        apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newRawSecret);//TODO :encode with BcryptPasswordEncoder
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));
        apiKey=apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(),apiKey.getKeyId(),newRawSecret,apiKey.getEnvironment());
    }


}
