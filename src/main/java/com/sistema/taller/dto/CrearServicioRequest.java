package com.sistema.taller.dto;

import lombok.Data;

@Data
public class CrearServicioRequest {
    private Long clienteId;
    private Long vehiculoId;
    private String descripcion;
}
