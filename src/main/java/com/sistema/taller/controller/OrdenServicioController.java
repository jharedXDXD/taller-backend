package com.sistema.taller.controller;

import com.sistema.taller.dto.CrearServicioRequest;
import com.sistema.taller.entity.Servicio;
import com.sistema.taller.service.OrdenServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes-servicio")
@RequiredArgsConstructor
public class OrdenServicioController {

    private final OrdenServicioService service;

    @PostMapping
    public Servicio crear(@RequestBody CrearServicioRequest req) {
        return service.crearOrden(req);
    }

    @PutMapping("/{id}/cerrar")
    public Servicio cerrar(
            @PathVariable Long id,
            @RequestParam BigDecimal total
    ) {
        return service.cerrarOrden(id, total);
    }

    @GetMapping
    public List<Servicio> listar() {
        return service.listar();
    }
}
