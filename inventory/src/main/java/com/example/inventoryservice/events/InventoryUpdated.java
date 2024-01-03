package com.example.inventoryservice.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryUpdated {
    private String productId;
    private Long stockRemain;
}