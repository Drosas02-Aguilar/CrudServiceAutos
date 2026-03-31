package com.crudAutos.Autos.DAO;

import com.crudAutos.Autos.Entity.Auto;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAuto extends JpaRepository<Auto, Integer> {

    List<Auto> findByMarcaIgnoreCase(String marca);

    List<Auto> findByAgenciaIdagencia(int idagencia);

    @Query(value = "SELECT IDAUTO, MARCA, MODELO, ANIO, COLOR, PRECIO, PLACA "
            + "FROM AUTOS "
            + "WHERE PRECIO BETWEEN :precioMin AND :precioMax",
            nativeQuery = true)
    List<Object[]> findAutosByPrecioBetween(@Param("precioMin") Double precioMin,
            @Param("precioMax") Double precioMax);

}
