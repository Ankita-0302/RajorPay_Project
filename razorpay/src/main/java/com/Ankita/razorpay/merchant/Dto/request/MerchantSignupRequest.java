package com.Ankita.razorpay.merchant.Dto.request;

import com.Ankita.razorpay.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignupRequest(
                                        @NotNull(message ="Name should be provided")
                                        @Size(max =50,message ="Name should not be more than 50 characters long ")
                                        String name,

                                        @Email
                                        @NotNull(message = "Email is required")
                                        String email,

                                        @NotNull(message = "Password is required")
                                        @Size(min=8 ,message ="Password Should be atleast 8 Characters long")
                                        String password,

                                        @Size(max=50,message ="Business  name should not be more than 50 characters long")
                                        String businessName,

                                        BusinessType businessType){


}



