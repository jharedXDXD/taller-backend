package com.sistema.taller.controller;

import com.sistema.taller.dto.AltaClienteDTO;
import com.sistema.taller.entity.Cliente;
import com.sistema.taller.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    // 🔹 LISTAR CLIENTES
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listar();
    }

    // 🔹 ALTA COMPLETA (CLIENTE + VEHÍCULO + GARANTÍA)
    @PostMapping("/alta-completa")
    public Cliente altaCompleta(@RequestBody AltaClienteDTO dto) {
        return clienteService.altaCompleta(dto);
    }

    // 🔹 ACTUALIZAR
    @PutMapping("/{id}")
    public Cliente actualizar(
            @PathVariable Long id,
            @RequestBody AltaClienteDTO dto
    ) {
        return clienteService.actualizar(id, dto);
    }

    // 🔹 ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
    }
}


