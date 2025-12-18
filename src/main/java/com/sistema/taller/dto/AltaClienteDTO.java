package com.sistema.taller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AltaClienteDTO {

    // CLIENTE
    private String nombre;
    private String telefono;
    private String correo;

    // VEHÍCULO
    private String tipoVehiculo;
    private String marca;
    private String modelo;
    private Integer anio;
    private String serieChasis;
    private String serieMotor;
    private String matricula;
    private LocalDate fechaCompra;
    private LocalDate fechaEntrada;

    // GARANTÍA
    private Boolean estadoGarantia;
}

