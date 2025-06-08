package com.cz.springboot_demo.service;

import com.cz.springboot_demo.exception.DeliveryNotFoundExceprion;
import com.cz.springboot_demo.exception.OrderAlreadyhaveADeliveryException;
import com.cz.springboot_demo.exception.OrderNotFoundException;
import com.cz.springboot_demo.pojo.Delivery;
import com.cz.springboot_demo.pojo.dto.DeliveryCreateDTO;
import com.cz.springboot_demo.pojo.dto.DeliveryUpdateDTO;
import com.cz.springboot_demo.repository.DeliveryRepository;
import com.cz.springboot_demo.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Since 2025/6/9 by CZ
@Service
public class DeliveryService implements IDeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Delivery createDelivery(DeliveryCreateDTO deliveryCreateDTO) {
        if (deliveryRepository.findByOrder_OrderId(deliveryCreateDTO.getOrderId()).isPresent()) {
            throw new OrderAlreadyhaveADeliveryException("Order already has a delivery info");
        } else {
            Delivery delivery = new Delivery();
            BeanUtils.copyProperties(deliveryCreateDTO, delivery);
            if (!orderRepository.findById(deliveryCreateDTO.getOrderId()).isPresent()) {
                throw new OrderNotFoundException("Order does not exist");
            } else {
                delivery.setOrder(orderRepository.findById(deliveryCreateDTO.getOrderId()).get());
            }
            return deliveryRepository.save(delivery);
        }
    }

    @Override
    public void deleteDelivery(Long deliveryId) {
        if (deliveryRepository.findById(deliveryId).isPresent()) {
            deliveryRepository.deleteById(deliveryId);
        } else {
            throw new DeliveryNotFoundExceprion("Delivery does not exist");
        }
    }

    @Override
    public Delivery getDeliveryById(Long deliveryId) {
        if (deliveryRepository.findById(deliveryId).isPresent()) {
            return deliveryRepository.findById(deliveryId).get();
        } else {
            throw new DeliveryNotFoundExceprion("Delivery does not exist");
        }
    }

    @Override
    public Delivery updateDelivery(Long deliveryId, DeliveryUpdateDTO deliveryUpdateDTO) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
        if (!optionalDelivery.isPresent()) {
            throw new DeliveryNotFoundExceprion("Delivery does not exist");
        }

        Delivery delivery = optionalDelivery.get();

        // ✅ 更新除 id 之外的属性（如果你不想覆盖某些字段，可以手动更新）
        BeanUtils.copyProperties(deliveryUpdateDTO, delivery, "id");

        // ✅ 保存更新后的 product
        return deliveryRepository.save(delivery);
    }
}
