package com.example.inventoryservice.service;

import com.example.inventoryservice.domain.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private Source source;

    @Mock
    private MessageChannel messageChannel;

    @InjectMocks
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(source.output()).thenReturn(messageChannel);
    }

    @Test
    void updateInventoryTest() {
        Inventory inventory = new Inventory();
        inventory.setProductId("testProduct");
        inventory.setStockRemain(10);

        when(inventoryRepository.findById(any())).thenReturn(Optional.of(inventory));
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
        when(messageChannel.send(any())).thenReturn(true);

        inventoryService.updateInventory("testProduct", 5);

        verify(inventoryRepository, times(1)).save(any(Inventory.class));
        verify(messageChannel, times(1)).send(any());
    }
}