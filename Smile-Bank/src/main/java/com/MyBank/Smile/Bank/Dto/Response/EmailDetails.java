package com.MyBank.Smile.Bank.Dto.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetails {
    private String recipient;
    private String messageBody;
    private String subject;
}
