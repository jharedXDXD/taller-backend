package com.sistema.taller.controller;

import com.sistema.taller.entity.Cliente;
import com.sistema.taller.entity.Vehiculo;
import com.sistema.taller.repository.ClienteRepository;
import com.sistema.taller.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes/{id}/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    private final ClienteRepository clienteRepo;
    private final VehiculoRepository vehiculoRepo;

    @PostMapping
    public Vehiculo guardar(
            @PathVariable Long id,
            @RequestBody Vehiculo v
    ) {
        Cliente c = clienteRepo.findById(id).orElseThrow();
        v.setCliente(c);
        return vehiculoRepo.save(v);
    }
}


