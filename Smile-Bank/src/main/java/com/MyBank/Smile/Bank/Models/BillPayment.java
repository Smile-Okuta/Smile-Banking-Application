package com.MyBank.Smile.Bank.Models;

import com.MyBank.Smile.Bank.dto.Request.DstvRequest;
import com.MyBank.Smile.Bank.dto.Request.NepaRequest;
import com.MyBank.Smile.Bank.dto.Request.WaterRequest;
import com.MyBank.Smile.Bank.Services.billPaymentService.DstvPayment;
import com.MyBank.Smile.Bank.Services.billPaymentService.NepaPayment;
import com.MyBank.Smile.Bank.Services.billPaymentService.WaterPayment;

public class BillPayment {
    DstvPayment dstvPayment;
    NepaPayment nepaPayment;
    WaterPayment waterPayment;

    public void payDstvBill(DstvRequest dstvRequest){
        dstvPayment.payDstvBill(dstvRequest);
    }

    public void payNepaBill(NepaRequest nepaRequest){
        nepaPayment.payNepaBill(nepaRequest);
    }

    public void payWaterBill(WaterRequest waterRequest){
        waterPayment.payWaterBill(waterRequest);
    }
}
