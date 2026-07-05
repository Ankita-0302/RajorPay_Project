package com.Ankita.razorpay.merchant.controller;


import com.Ankita.razorpay.merchant.Dto.request.CreateApiKeyRequest;
import com.Ankita.razorpay.merchant.Dto.resonse.ApiKeyCreateResponse;
import com.Ankita.razorpay.merchant.Dto.resonse.ApiKeyResponse;
import com.Ankita.razorpay.merchant.service.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/merchants/{merchantId}/api-keys")
@RequiredArgsConstructor
public class ApikeyController {

    private final ApiKeyService apiKeyService ;

    @PostMapping
    public ResponseEntity<ApiKeyCreateResponse> create(@PathVariable UUID merchantId, @Valid @RequestBody CreateApiKeyRequest request){

        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(apiKeyService.create(merchantId,request));
    }

    @GetMapping
    public ResponseEntity<List<ApiKeyResponse>> list(@PathVariable UUID merchantId) {
        return ResponseEntity.ok(apiKeyService.listByMerchant(merchantId));
    }

    @DeleteMapping("/keyId")
    public ResponseEntity<Void> revoke(@PathVariable UUID merchantId,@PathVariable UUID keyId){
        apiKeyService.revoke(merchantId,keyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{keyId}/rotate")
    public ResponseEntity<ApiKeyCreateResponse> rotateKey(@PathVariable UUID merchantId,@PathVariable UUID keyId){
        return ResponseEntity.ok(apiKeyService.rotate(merchantId,keyId));
    }

}
