package com.MyBank.Smile.Bank.Controllers;

import com.MyBank.Smile.Bank.dto.Request.NameEnquiryRequest;
import com.MyBank.Smile.Bank.dto.Request.CreateUserRequest;
import com.MyBank.Smile.Bank.dto.Response.BankResponse;
import com.MyBank.Smile.Bank.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("account")
@Controller
public class UserController {
    private final UserService userService;
        @PostMapping("create_user")
    public BankResponse createUser(@RequestBody CreateUserRequest createUserRequest){
            return userService.createUser(createUserRequest);
    }

    @GetMapping("nameEnquiry")
    public String nameEnquiry(@RequestBody NameEnquiryRequest request){
            return userService.nameEnquiry(request);
    }
}
