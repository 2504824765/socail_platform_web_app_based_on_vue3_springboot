package com.cz.springboot_demo.repository;

import com.cz.springboot_demo.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public Optional<Order> findByOrderName(String oder_name);
}
