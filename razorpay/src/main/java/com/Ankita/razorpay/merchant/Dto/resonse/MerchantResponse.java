package com.Ankita.razorpay.merchant.Dto.resonse;

import com.Ankita.razorpay.common.enums.BusinessType;
import com.Ankita.razorpay.common.enums.MerChantStatus;
import org.hibernate.validator.constraints.UUID;

public record MerchantResponse(
                                UUID id,
                                String name ,
                                String email,
                                String businessName,
                                BusinessType BusinessType,
                                MerChantStatus merChantStatus
) {
}
