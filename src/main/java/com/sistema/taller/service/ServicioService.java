package com.sistema.taller.service;

import com.sistema.taller.entity.TipoVehiculo;
import com.sistema.taller.repository.PrecioServicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ServicioService {

    private final PrecioServicioRepository precioRepo;

    public BigDecimal calcularPrecio(
            TipoVehiculo tipoVehiculo,
            String servicio
    ) {
        return precioRepo
                .findByTipoVehiculoAndServicio(tipoVehiculo, servicio)
                .map(p -> p.getPrecioBase())
                .orElse(BigDecimal.ZERO);
    }
}
