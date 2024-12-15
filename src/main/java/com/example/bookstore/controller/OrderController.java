package com.example.bookstore.controller;

import com.example.bookstore.model.Order;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bookstore.model.Transaction;
import com.example.bookstore.model.Transaction.PaymentMethod;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;
    private final TransactionService transactionService;

    public OrderController(OrderService orderService, TransactionService transactionService) {
        this.orderService = orderService;
        this.transactionService =  transactionService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createOrder(@RequestParam Long userId) {
        Order order = orderService.makeOrder(userId);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{orderId}/pay")
    public ResponseEntity<Transaction> processTransaction(
            @PathVariable Long orderId,
            @RequestParam PaymentMethod paymentMethod) {
        Order order = orderService.getOrderById(orderId); // Method to fetch order by ID
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("order: " + order);
        Transaction transaction = transactionService.processTransaction(order, paymentMethod);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
    Order order = orderService.getOrderById(id);
    return ResponseEntity.ok(order);
    }
}
