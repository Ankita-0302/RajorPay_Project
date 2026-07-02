package com.Ankita.razorpay.merchant.service;

import com.Ankita.razorpay.merchant.Dto.request.MerchantSignupRequest;
import com.Ankita.razorpay.merchant.Dto.resonse.MerchantResponse;

public interface AuthService {

    MerchantResponse signup(MerchantSignupRequest request);

}
