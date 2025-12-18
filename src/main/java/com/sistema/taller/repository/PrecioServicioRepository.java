package com.sistema.taller.repository;

import com.sistema.taller.entity.PrecioServicio;
import com.sistema.taller.entity.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrecioServicioRepository
        extends JpaRepository<PrecioServicio, Long> {

    Optional<PrecioServicio> findByTipoVehiculoAndServicio(
            TipoVehiculo tipoVehiculo,
            String servicio
    );
}
