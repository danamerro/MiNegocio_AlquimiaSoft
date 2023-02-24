package com.alquimiaSoft.miNegocio.repository;

import com.alquimiaSoft.miNegocio.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    @Query(value = "SELECT * FROM CLIENTE c WHERE c.NUMERO_IDENTIFICACION = ?1", nativeQuery = true)
    Optional<List<Cliente>> findByNumeroIdentificacion(int numeroIdentificacion);

    @Query(value = "SELECT * FROM CLIENTE c WHERE LOWER(c.NOMBRES) = LOWER(?1)", nativeQuery = true)
    Optional<List<Cliente>> findByNombre(String nombres);

    @Query(value = "SELECT * FROM CLIENTE c WHERE c.NUMERO_IDENTIFICACION = ?1  and  LOWER(c.NOMBRES) = LOWER(?2)", nativeQuery = true)
    Optional<List<Cliente>> findByNumeroIdentificacionYNombre(int numeroIdentificacion, String nombres );

}
