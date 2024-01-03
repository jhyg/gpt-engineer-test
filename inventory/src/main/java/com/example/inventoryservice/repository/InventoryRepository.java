package com.example.inventoryservice.repository;

import com.example.inventoryservice.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InventoryRepository extends JpaRepository<Inventory, String> {
}