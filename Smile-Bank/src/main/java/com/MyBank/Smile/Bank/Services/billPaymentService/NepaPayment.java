package com.MyBank.Smile.Bank.Services.billPaymentService;

import com.MyBank.Smile.Bank.dto.Request.NepaRequest;

public interface NepaPayment {
    String payNepaBill(NepaRequest nepaRequest);
}
