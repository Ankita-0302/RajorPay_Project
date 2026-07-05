package com.Ankita.razorpay.common.util;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

public class RandomizerUtil {

    private static final SecureRandom SECURE_RANDOM=new SecureRandom();

    public static String randomBase64(int length){
        //UUID.randomUUID().toString().replace("-","");

        byte[] buf =new byte[length/2];
        SECURE_RANDOM.nextBytes(buf);
        //[4,12,100,-12] {-128,127}
        return Base64.getUrlEncoder().withoutPadding().encodeToString(buf);
    }

}
