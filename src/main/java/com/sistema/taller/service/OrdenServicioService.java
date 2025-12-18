package com.sistema.taller.service;

import com.sistema.taller.dto.CrearServicioRequest;
import com.sistema.taller.entity.*;
import com.sistema.taller.repository.ClienteRepository;
import com.sistema.taller.repository.ServicioRepository;
import com.sistema.taller.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenServicioService {

    private final ServicioRepository servicioRepo;
    private final ClienteRepository clienteRepo;
    private final VehiculoRepository vehiculoRepo;

    public Servicio crearOrden(CrearServicioRequest req) {

        Cliente cliente = clienteRepo.findById(req.getClienteId()).orElseThrow();
        Vehiculo vehiculo = vehiculoRepo.findById(req.getVehiculoId()).orElseThrow();

        Servicio servicio = new Servicio();
        servicio.setCliente(cliente);
        servicio.setVehiculo(vehiculo);
        servicio.setDescripcion(req.getDescripcion());
        servicio.setEstado(EstadoServicio.PENDIENTE);
        servicio.setFechaIngreso(LocalDate.now());
        servicio.setTotal(BigDecimal.ZERO);

        return servicioRepo.save(servicio);
    }

    public Servicio cerrarOrden(Long idServicio, BigDecimal total) {

        Servicio servicio = servicioRepo.findById(idServicio).orElseThrow();
        servicio.setEstado(EstadoServicio.FINALIZADO);
        servicio.setFechaSalida(LocalDate.now());
        servicio.setTotal(total);

        return servicioRepo.save(servicio);
    }

    public List<Servicio> listar() {
        return servicioRepo.findAll();
    }
}
