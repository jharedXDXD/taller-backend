package com.sistema.taller.service;

import com.sistema.taller.dto.AltaClienteDTO;
import com.sistema.taller.entity.Cliente;
import com.sistema.taller.entity.Garantia;
import com.sistema.taller.entity.Vehiculo;
import com.sistema.taller.repository.ClienteRepository;
import com.sistema.taller.repository.GarantiaRepository;
import com.sistema.taller.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepo;
    private final VehiculoRepository vehiculoRepo;
    private final GarantiaRepository garantiaRepo;

    public List<Cliente> listar() {
        return clienteRepo.findAll();
    }

    public Cliente altaCompleta(AltaClienteDTO dto) {

        Cliente cliente = Cliente.builder()
                .nombre(dto.getNombre())
                .telefono(dto.getTelefono())
                .correo(dto.getCorreo())
                .build();

        clienteRepo.save(cliente);

        Garantia garantia = Garantia.builder()
                .estadoGarantia(dto.getEstadoGarantia())
                .build();
        garantiaRepo.save(garantia);

        Vehiculo vehiculo = Vehiculo.builder()
                .cliente(cliente)
                .tipoVehiculo(dto.getTipoVehiculo())
                .marca(dto.getMarca())
                .modelo(dto.getModelo())
                .anio(dto.getAnio())
                .serieChasis(dto.getSerieChasis())
                .serieMotor(dto.getSerieMotor())
                .matricula(dto.getMatricula())
                .fechaCompra(dto.getFechaCompra())
                .fechaEntrada(dto.getFechaEntrada())
                .garantia(garantia)
                .build();

        vehiculoRepo.save(vehiculo);

        return cliente;
    }

    public Cliente actualizar(Long id, AltaClienteDTO dto) {
        eliminar(id);
        return altaCompleta(dto);
    }

    public void eliminar(Long id) {
        clienteRepo.deleteById(id);
    }
}
