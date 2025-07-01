package com.cibertec.controller;

import com.cibertec.service.ApiClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
@SessionAttributes("idUsuario")
public class TiendaController {

    @Autowired
    private ApiClienteService apiClienteService;

    @ModelAttribute("idUsuario")
    public int idUsuario() {
        return 1; // Simula usuario logueado (más adelante lo puedes reemplazar)
    }

    @GetMapping("productos")
    public String verProductos(Model model) {
        model.addAttribute("productos", apiClienteService.obtenerProductos());
        return "productos";
    }

    @PostMapping("carrito/agregar")
    public String agregarProducto(@RequestParam int idProducto, @RequestParam int cantidad,
                                  @ModelAttribute("idUsuario") int idUsuario) {
        apiClienteService.agregarAlCarrito(idProducto, cantidad, idUsuario);
        return "redirect:/productos";
    }

    @GetMapping("carrito")
    public String verCarrito(Model model, @ModelAttribute("idUsuario") int idUsuario) {
        List<Map<String, Object>> carrito = apiClienteService.obtenerCarrito(idUsuario);
        model.addAttribute("carrito", carrito);
        return "carrito";
    }

    @PostMapping("carrito/eliminar")
    public String eliminarDelCarrito(@RequestParam int idProducto,
                                     @ModelAttribute("idUsuario") int idUsuario) {
        apiClienteService.eliminarDelCarrito(idProducto, idUsuario);
        return "redirect:/carrito";
    }
}
