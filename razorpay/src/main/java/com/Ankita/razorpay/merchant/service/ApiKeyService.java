package com.Ankita.razorpay.merchant.service;

import com.Ankita.razorpay.merchant.Dto.request.CreateApiKeyRequest;
import com.Ankita.razorpay.merchant.Dto.resonse.ApiKeyCreateResponse;
import com.Ankita.razorpay.merchant.Dto.resonse.ApiKeyResponse;

import java.util.List;
import java.util.UUID;


public interface ApiKeyService {

    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);

    List<ApiKeyResponse> listByMerchant(UUID merchantId);

    void revoke(UUID merchantId, UUID keyId);

    ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId);
}
