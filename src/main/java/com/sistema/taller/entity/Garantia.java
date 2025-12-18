package com.sistema.taller.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "garantia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Garantia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_garantia")
    private Long idGarantia;

    private Boolean estadoGarantia;

    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    @JsonIgnoreProperties("garantia")
    private Vehiculo vehiculo;
}
