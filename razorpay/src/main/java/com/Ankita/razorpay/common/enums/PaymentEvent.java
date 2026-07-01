package com.Ankita.razorpay.common.enums;

public enum PaymentEvent {

    AUTHORIZE_ATTEMPT,
    AUTHORIZED_SUCCESS,
    AUTHORIZED_FAIL,
    CAPTURE_REQUEST,
    CAPTURE_SUCCESS,
    CAPTURE_FAIL,
    REFUND_INIT,
    REFUND_COMPLETE,
    SETTLE,
    CANCEL,
    CAPTURE_TIMEOUT

}
