package com.sistema.taller.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "vehiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Long idVehiculo;

    private String tipoVehiculo;
    private String marca;
    private String modelo;
    private Integer anio;

    private String serieChasis;
    private String serieMotor;
    private String matricula;

    private LocalDate fechaCompra;
    private LocalDate fechaEntrada;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("vehiculos")
    private Cliente cliente;

    @OneToOne(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("vehiculo")
    private Garantia garantia;
}
