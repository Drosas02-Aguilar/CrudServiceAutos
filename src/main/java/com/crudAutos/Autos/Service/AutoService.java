package com.crudAutos.Autos.Service;

import com.crudAutos.Autos.DAO.IAgencia;
import com.crudAutos.Autos.DAO.IAuto;
import com.crudAutos.Autos.Entity.Agencia;
import com.crudAutos.Autos.Entity.Auto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutoService {
    
    @Autowired
    private IAuto iAuto;
    
    @Autowired
    private IAgencia iAgencia;
    
    public List<Auto> ConsultarAutos() {
        return iAuto.findAll();
    }
    
    public Auto GetAutoById(int idauto) {
        Optional<Auto> auto = iAuto.findById(idauto);
        return auto.isPresent() ? auto.get() : null;
    }
    
    public List<Auto> ConsultarAutosPorMarca(String marca) {
        return iAuto.findByMarcaIgnoreCase(marca);
    }
    
    public List<Auto> ConsultarAutosPorAgencia(int idagencia) {
        return iAuto.findByAgenciaIdagencia(idagencia);
    }
    
    public List<Auto> ConsultarAutosPorPrecio(Double precioMin, Double precioMax) {
        List<Object[]> resultados = iAuto.findAutosByPrecioBetween(precioMin, precioMax);
        
        List<Auto> autos = new ArrayList<>();
        for(Object[] fila : resultados){
            Auto auto = new Auto();
            auto.setIdauto((int) ((BigDecimal) fila[0]).intValue());
            auto.setMarca((String)fila[1]);
            auto.setModelo((String)fila[2]);
            auto.setAnio((String)fila[3]);
            auto.setColor((String)fila[4]);
            auto.setPrecio((int) ((BigDecimal) fila[5]).doubleValue());
            auto.setPlaca((String)fila[6]);            
            autos.add(auto);
        
        }
                return autos;
                
        
        
    }
    
    @Transactional
    public Auto CrearAuto(Auto auto) {
        return iAuto.save(auto);
    }
    
    @Transactional
    public Auto EditarAuto(int idauto, Auto autoUpdate) {
        Optional<Auto> autoOptional = iAuto.findById(idauto);
        
        if (autoOptional.isPresent()) {
            Auto auto = autoOptional.get();
            auto.setMarca(autoUpdate.getMarca());
            auto.setModelo(autoUpdate.getModelo());
            auto.setAnio(autoUpdate.getAnio());
            auto.setColor(autoUpdate.getColor());
            auto.setPrecio(autoUpdate.getPrecio());
            auto.setPlaca(autoUpdate.getPlaca());
            
            if (autoUpdate.getAgencia() != null) {
                Optional<Agencia> agenciaOptional = iAgencia.findById(autoUpdate.getAgencia().getIdagencia());
                auto.setAgencia(agenciaOptional.isPresent() ? agenciaOptional.get() : null);
            }
            return iAuto.save(auto);
        }
        return null;
    }
    
    @Transactional
    public Auto ElimianAuto(int idauto) {
        Optional<Auto> autoOptional = iAuto.findById(idauto);
        
        if (autoOptional.isPresent()) {
            Auto auto = autoOptional.get();
            iAuto.delete(auto);
            return auto;
        }
        return null;
    }
}
