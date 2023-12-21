package com.MyBank.Smile.Bank.Services.billPaymentService;

import com.MyBank.Smile.Bank.dto.Request.DstvRequest;

public interface DstvPayment {
    String payDstvBill(DstvRequest dstvRequest);
}
