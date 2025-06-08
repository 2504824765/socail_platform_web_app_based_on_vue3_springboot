package com.cz.springboot_demo.repository;

import com.cz.springboot_demo.pojo.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Optional<Delivery> findByOrder_OrderId(Long orderId);
}
