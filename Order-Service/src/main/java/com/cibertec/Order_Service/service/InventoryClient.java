package com.cibertec.Order_Service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", path = "/api/inventory")
public interface InventoryClient {

    @PutMapping("/{productoId}/check")
    void checkStock(@PathVariable Integer productoId, @RequestParam Integer cantidad);

    @PutMapping("/{productoId}/update")
    void updateStock(@PathVariable Integer productoId, @RequestParam Integer cantidad);
}
