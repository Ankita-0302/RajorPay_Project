package com.Ankita.razorpay.merchant.service;

import com.Ankita.razorpay.merchant.Dto.request.CreateApiKeyRequest;
import com.Ankita.razorpay.merchant.Dto.resonse.ApiKeyCreateResponse;

import java.util.UUID;


public interface ApiKeyService {

    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);

}
