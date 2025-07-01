package com.cibertec.Order_Service.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", path = "/api/productos")
public interface InventoryClient {

    @PutMapping("/{id}/check")
    void checkStock(@PathVariable("id") Integer id, @RequestParam("cantidad") Integer cantidad);

    @PutMapping("/{id}/stock")
    void updateStock(@PathVariable("id") Integer id, @RequestParam("cantidad") Integer cantidad);
}
