package com.example.inventoryservice.repository;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Modifying
    @Query("UPDATE Inventory i SET i.quantity = i.quantity - :deduct WHERE i.skuCode = :skuCode AND i.quantity >= :deduct")
    int deductStock(@Param("skuCode") String skuCode, @Param("deduct") Integer deduct);

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
