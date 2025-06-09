package com.cz.springboot_demo.controller;

import com.cz.springboot_demo.pojo.Delivery;
import com.cz.springboot_demo.pojo.dto.DeliveryCreateDTO;
import com.cz.springboot_demo.pojo.dto.DeliveryUpdateDTO;
import com.cz.springboot_demo.pojo.dto.ResponseMessage;
import com.cz.springboot_demo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Since 2025/6/9 by CZ
@CrossOrigin
@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public ResponseMessage createDelivery(@RequestBody DeliveryCreateDTO deliveryCreateDTO) {
        return ResponseMessage.success("Create delivery successfully", deliveryService.createDelivery(deliveryCreateDTO));
    }

    @DeleteMapping("/{deliveryId}")
    public ResponseMessage deleteDelivery(@PathVariable("deliveryId") Long deliveryId) {
        deliveryService.deleteDelivery(deliveryId);
        return ResponseMessage.success("Delete delivery successfully");
    }

    @GetMapping("byDeliveryId/{deliveryId}")
    public ResponseMessage getDeliveryById(@PathVariable("deliveryId") Long deliveryId) {
        return ResponseMessage.success("Get delivery by delivery id successfully", deliveryService.getDeliveryById(deliveryId));
    }

    @PutMapping("/{deliveryId}")
    public ResponseMessage updateDelivery(@PathVariable("deliveryId") Long deliveryId, @RequestBody DeliveryUpdateDTO deliveryUpdateDTO) {
        return ResponseMessage.success("Update delivery successfully", deliveryService.updateDelivery(deliveryId, deliveryUpdateDTO));
    }

    @GetMapping("/all")
    public ResponseMessage getAllDeliveries() {
        return ResponseMessage.success("Get all deliveries successfully", deliveryService.getAllDeliveries());
    }

    @GetMapping("/count")
    public ResponseMessage getDeliveryCount() {
        return ResponseMessage.success("Get delivery count successfully", deliveryService.getDeliveryCount());
    }

}
