package com.myshop.inventory.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myshop.inventory.entity.InventoryItem;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    Optional<InventoryItem> findByProductId(UUID productId);

    Optional<InventoryItem> findBySku(String sku);

    boolean existsByProductId(UUID productId);

    boolean existsBySku(String sku);

}