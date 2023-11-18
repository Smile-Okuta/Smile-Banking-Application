package com.MyBank.Smile.Bank.Controllers;

import com.MyBank.Smile.Bank.Dto.UserRequest;
import com.MyBank.Smile.Bank.Dto.BankResponse;
import com.MyBank.Smile.Bank.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("account")
@Controller
public class UserController {
    private final UserService accountService;
        @PostMapping("create_account")
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
            return accountService.createAccount(userRequest);
    }


}
