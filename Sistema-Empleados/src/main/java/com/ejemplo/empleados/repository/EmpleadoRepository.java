package com.ejemplo.empleados.repository;

import com.ejemplo.empleados.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findAllByOrderByApellidoAscNombreAsc();

    boolean existsByEmailAndIdNot(String email, Long id);
}
