package com.cz.springboot_demo.service;

import com.cz.springboot_demo.pojo.Delivery;
import com.cz.springboot_demo.pojo.dto.DeliveryCreateDTO;
import com.cz.springboot_demo.pojo.dto.DeliveryUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public interface IDeliveryService {
    Delivery createDelivery(DeliveryCreateDTO deliveryCreateDTO);

    void deleteDelivery(Long deliveryId);

    Delivery getDeliveryById(Long deliveryId);

    Delivery updateDelivery(Long deliveryId, DeliveryUpdateDTO deliveryUpdateDTO);
}
