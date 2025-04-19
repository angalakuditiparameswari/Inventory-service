package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.dto.OrderItemsDto;
import com.example.inventoryservice.dto.OrderRequest;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory->InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity()>0)
                        .build()).toList();
    }

    @Transactional
    public void updateStock(OrderRequest orderRequest) {
        List<OrderItemsDto> orderItemsDtoList = orderRequest.getOrderItemsDtoList();
        for (OrderItemsDto orderItemsDto : orderItemsDtoList) {
            System.out.println(inventoryRepository.deductStock(orderItemsDto.getSkuCode(), orderItemsDto.getQuantity()) > 0 ? "deductStock" : "not deductStock");
        }
    }
}
