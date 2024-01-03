package com.example.inventoryservice.service;

import com.example.inventoryservice.domain.Inventory;
import com.example.inventoryservice.events.InventoryUpdated;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final Source source;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, Source source) {
        this.inventoryRepository = inventoryRepository;
        this.source = source;
    }

    @Transactional
    public void updateInventory(String productId, Integer stockRemain) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        inventory.setStockRemain(stockRemain);
        inventoryRepository.save(inventory);

        InventoryUpdated event = new InventoryUpdated(productId, stockRemain.longValue());
        source.output().send(MessageBuilder.withPayload(event).build());
    }
}