package com.sistema.taller.repository;

import com.sistema.taller.entity.Cliente;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("""
        SELECT DISTINCT c
        FROM Cliente c
        JOIN c.vehiculos v
        JOIN v.garantia g
        WHERE g.estadoGarantia = true
    """)
    List<Cliente> clientesConGarantia();
}
