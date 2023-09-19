//package com.MyBank.Smile.Bank.Controllers;
//
//import com.MyBank.Smile.Bank.Dto.BankResponse;
//import com.MyBank.Smile.Bank.Dto.CustomerRequest;
//import com.MyBank.Smile.Bank.Services.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/customer")
//public class CustomerController {
//
//    @Autowired
//    CustomerService customerService;
//
//    @PostMapping()
//    public BankResponse createAccount(@RequestBody CustomerRequest customerRequest) {
//        return customerService.createAccount(customerRequest);
//    }
//}
