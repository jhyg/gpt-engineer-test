package com.example.inventoryservice.controller;

import com.example.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<?> updateInventory(@PathVariable String productId, @RequestParam Integer stockRemain) {
        inventoryService.updateInventory(productId, stockRemain);
        return ResponseEntity.ok().build();
    }
}