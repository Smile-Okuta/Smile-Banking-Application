package com.MyBank.Smile.Bank.dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailDetails {
    private String recipient;
    private String messageBody;
    private CreateUserResponse subject;
}
