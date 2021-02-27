package com.aditya.banking.system.demo.controller;

import com.aditya.banking.system.demo.entity.constant.ApiPath;
import com.aditya.banking.system.demo.model.request.BankAccountModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping(ApiPath.BANK_ACCOUNT)
public class BankAccountController {

    private static final Logger LOG = LoggerFactory.getLogger(BankAccountController.class);

    @RequestMapping(value = "/createBankAccount", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(@RequestHeader(value = "userId", defaultValue = "1234567") String userId,
                                                @RequestBody BankAccountModel accountModel) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/getBalance", method = RequestMethod.GET)
    public ResponseEntity<Object> getAccountBalance(@RequestHeader(value = "userId", defaultValue = "1234567") String userId,
                                                    @RequestParam Long accountNumber) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/transferMoney", method = RequestMethod.PUT)
    public ResponseEntity<Object> transferMoney(@RequestHeader(value = "userId", defaultValue = "1234567") String userId,
                                                @RequestParam Long fromAccount, @RequestParam Long toAccount, @RequestParam Double transferAmount) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/printAccountStatement", method = RequestMethod.PUT)
    public ResponseEntity<Object> printAccountStatement(@RequestHeader(value = "userId", defaultValue = "1234567") String userId,
                                                        @RequestParam Long accountNumber) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/calculateInterest", method = RequestMethod.PUT)
    public ResponseEntity<Object> calculateInterest(@RequestHeader(value = "userId", defaultValue = "1234567") String userId,
                                                    @RequestParam Long accountNumber, @RequestParam Long yearsOldAccount) throws IOException {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}