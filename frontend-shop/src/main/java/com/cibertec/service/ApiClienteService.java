package com.cibertec.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiClienteService {

    @Value("${url.inventory}")
    private String inventoryUrl;

    @Value("${url.orders}")
    private String ordersUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Map<String, Object>> obtenerProductos() {
        ResponseEntity<List> response = restTemplate.getForEntity(inventoryUrl + "/productos", List.class);
        return response.getBody();
    }


    public void agregarAlCarrito(int idProducto, int cantidad, int idUsuario) {
        Map<String, Object> datos = new HashMap<>();
        datos.put("idProducto", idProducto);
        datos.put("cantidad", cantidad);
        datos.put("idUsuario", idUsuario);

        restTemplate.postForEntity(ordersUrl + "/carrito/agregar", datos, Void.class);
    }

    public List<Map<String, Object>> obtenerCarrito(int idUsuario) {
        return restTemplate.getForObject(ordersUrl + "/carrito/" + idUsuario, List.class);
    }

    public void eliminarDelCarrito(int idProducto, int idUsuario) {
        restTemplate.delete(ordersUrl + "/carrito/eliminar?idProducto=" + idProducto + "&idUsuario=" + idUsuario);
    }
}
