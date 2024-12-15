package com.example.bookstore.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookstore.model.BookCart;

public interface BookCartRepository extends JpaRepository<BookCart, Long> {
  Optional<BookCart> findByUserId(Long userId);
}
