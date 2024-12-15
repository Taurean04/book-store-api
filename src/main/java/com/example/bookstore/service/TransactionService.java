package com.example.bookstore.service;

import com.example.bookstore.model.Order;
import com.example.bookstore.model.Order.StatusEnum;
import com.example.bookstore.model.Transaction;
import com.example.bookstore.model.Transaction.PaymentMethod;
import com.example.bookstore.model.Transaction.PaymentStatus;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.TransactionRepository;

import com.example.bookstore.exception.PaymentProcessingException;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
	// private final BookCartRepository bookCartRepository;
	private final OrderRepository orderRepository;

	public TransactionService(TransactionRepository transactionRepository, OrderRepository orderRepository) {
		this.transactionRepository = transactionRepository;
		this.orderRepository = orderRepository;
	}

	public Transaction processTransaction(Order order, PaymentMethod paymentMethod) {
		Transaction transaction = new Transaction();
		transaction.setOrder(order);
		transaction.setAmount(order.getTotalPrice());
		transaction.setPaymentMethod(paymentMethod);

		try {
			// Simulated payment logic
			boolean paymentSuccessful = simulatePayment(paymentMethod, order.getTotalPrice());
			transaction.setStatus(paymentSuccessful ? PaymentStatus.valueOf("SUCCESS") : PaymentStatus.valueOf("FAILED"));
		} catch (Exception e) {
			transaction.setStatus(PaymentStatus.valueOf("FAILED"));
			throw new PaymentProcessingException("Payment failed: " + e.getMessage());
		}

		if(transaction.getStatus() == PaymentStatus.valueOf("SUCCESS")) order.setStatus(StatusEnum.valueOf("COMPLETED"));

		orderRepository.save(order);
		// Save the transaction log
		return transactionRepository.save(transaction);
	}

	private boolean simulatePayment(PaymentMethod paymentMethod, double amount) {
		// Simulate payment success or failure
		return Math.random() > 0.2; // 80% chance of success
	}
}
