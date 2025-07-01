package com.cibertec.inventory_service.controller;

import com.cibertec.inventory_service.entity.Producto;
import com.cibertec.inventory_service.service.AuthService;
import com.cibertec.inventory_service.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private AuthService authService; // validación con login-service

    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        List<Producto> productos = productoService.listarProductos();
        model.addAttribute("productos", productos);
        return "productos";
    }

    @PostMapping("/productos/modificar-stock/{codigo}")
    public String modificarStock(@PathVariable Integer codigo,
                                 @RequestParam int stock,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 RedirectAttributes redirect) {

        // Validar si el usuario es admin
        if (!authService.esAdmin(username, password)) {
            redirect.addFlashAttribute("error", "No autorizado. Solo ADMIN puede modificar el stock.");
            return "redirect:/productos";
        }

        // Buscar el producto y actualizar stock
        productoService.obtenerProductoPorCodigo(codigo).ifPresent(producto -> {
            producto.setStock(stock);
            productoService.actualizarProducto(producto);
        });

        // Mensaje de éxito
        redirect.addFlashAttribute("success", "Stock actualizado correctamente.");
        return "redirect:/productos";
    }
}
