package com.sistema.taller.controller;

import com.sistema.taller.entity.Cliente;
import com.sistema.taller.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    // 🔹 LISTAR TODOS
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // 🔹 INSERTAR CLIENTE + VEHÍCULO + GARANTÍA
    @PostMapping
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        cliente.getVehiculos().forEach(v -> {
            v.setCliente(cliente);
            if (v.getGarantia() != null) {
                v.getGarantia().setVehiculo(v);
            }
        });
        return clienteRepository.save(cliente);
    }

    // 🔹 CLIENTES CON VEHÍCULOS EN GARANTÍA
    @GetMapping("/con-garantia")
    public List<Cliente> clientesConGarantia() {
        return clienteRepository.clientesConGarantia();
    }
}
