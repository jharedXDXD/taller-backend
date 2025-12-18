package com.sistema.taller.controller;

import com.sistema.taller.entity.TipoVehiculo;
import com.sistema.taller.service.ServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/servicios")
@RequiredArgsConstructor
public class ServicioController {

    private final ServicioService servicioService;

    @PostMapping("/cotizar")
    public BigDecimal cotizar(
            @RequestParam TipoVehiculo tipoVehiculo,
            @RequestParam String servicio
    ) {
        return servicioService.calcularPrecio(tipoVehiculo, servicio);
    }
}

