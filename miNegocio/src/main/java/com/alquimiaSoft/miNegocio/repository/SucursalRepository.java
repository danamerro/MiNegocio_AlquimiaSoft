package com.alquimiaSoft.miNegocio.repository;

import com.alquimiaSoft.miNegocio.domain.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SucursalRepository extends JpaRepository<Sucursal,Long>{
    @Query(value = "SELECT * FROM SUCURSAL s WHERE LOWER(s.CIUDAD) = LOWER(?1) and LOWER(s.PROVINCIA) = LOWER(?2) and LOWER(s.DIRECCION) = LOWER(?3)", nativeQuery = true)
    Optional<Sucursal> findByCiudadProvinciaDireccion(String ciudad,String provincia, String direccion);
}
