package com.sistema.taller.repository;

import com.sistema.taller.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    List<Vehiculo> findByClienteIdCliente(Long idCliente);
}
