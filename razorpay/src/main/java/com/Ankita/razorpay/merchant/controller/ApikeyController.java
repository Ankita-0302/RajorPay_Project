package com.Ankita.razorpay.merchant.controller;


import com.Ankita.razorpay.merchant.Dto.request.CreateApiKeyRequest;
import com.Ankita.razorpay.merchant.Dto.resonse.ApiKeyCreateResponse;
import com.Ankita.razorpay.merchant.service.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/merchants/{merchantId}/api-key")
@RequiredArgsConstructor
public class ApikeyController {

    private final ApiKeyService apikeyService ;

    @PostMapping
    public ResponseEntity<ApiKeyCreateResponse> create(@PathVariable UUID merchantId, @Valid @RequestBody CreateApiKeyRequest request){

        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(apikeyService.create(merchantId,request));

    }

}
