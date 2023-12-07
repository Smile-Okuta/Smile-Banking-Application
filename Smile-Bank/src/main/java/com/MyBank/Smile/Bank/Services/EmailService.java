package com.MyBank.Smile.Bank.Services;

import com.MyBank.Smile.Bank.Dto.Response.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
