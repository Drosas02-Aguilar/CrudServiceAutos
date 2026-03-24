package com.crudAutos.Autos.DAO;

import com.crudAutos.Autos.Entity.Agencia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAgencia  extends JpaRepository<Agencia, Integer>{
   List<Agencia>findByCiudadIgnoreCase(String ciudad);
   boolean existsByNombre(String nombre);
}
