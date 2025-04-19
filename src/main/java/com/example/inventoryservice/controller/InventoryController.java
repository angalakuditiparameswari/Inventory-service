package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.dto.OrderRequest;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateStock(@RequestBody OrderRequest orderRequest) {
        inventoryService.updateStock(orderRequest);
    }

    /*// manual create inventory - not a realtime scenario
    private final InventoryRepository inventoryRepository;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertInventory(@RequestBody Inventory inventory) {
        inventoryRepository.save(inventory);
    }
    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }*/

}

