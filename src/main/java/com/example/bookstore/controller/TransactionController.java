package com.example.bookstore.controller;

import com.example.bookstore.dto.*;
import com.example.bookstore.model.Transaction;
import com.example.bookstore.model.Transaction.PaymentStatus;
import com.example.bookstore.service.TransactionService;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
}
