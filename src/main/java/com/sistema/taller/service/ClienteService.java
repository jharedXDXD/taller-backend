package com.sistema.taller.service;

import com.sistema.taller.entity.Cliente;
import com.sistema.taller.entity.Garantia;
import com.sistema.taller.entity.Vehiculo;
import com.sistema.taller.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    /**
     * Guarda Cliente + Vehículos + Garantía
     * Todo se persiste por CASCADE
     */
    public Cliente guardarClienteCompleto(Cliente cliente) {

        if (cliente.getVehiculos() != null) {
            for (Vehiculo vehiculo : cliente.getVehiculos()) {

                // Relación Cliente -> Vehículo
                vehiculo.setCliente(cliente);

                // Relación Vehículo -> Garantía
                Garantia garantia = vehiculo.getGarantia();
                if (garantia != null) {
                    garantia.setVehiculo(vehiculo);
                }
            }
        }

        // 🔥 Guarda todo en cascada
        return clienteRepository.save(cliente);
    }

    /**
     * Listar todos los clientes
     */
    public Iterable<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}
