package com.MyBank.Smile.Bank.Services;

import com.MyBank.Smile.Bank.dto.Response.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
