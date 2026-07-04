package com.Ankita.razorpay.merchant.Dto.resonse;

import com.Ankita.razorpay.common.enums.Environment;

import java.util.UUID;

public record ApiKeyCreateResponse(

    UUID id,
    String keyId,
    String keySecret,
    Environment environment
){

}
