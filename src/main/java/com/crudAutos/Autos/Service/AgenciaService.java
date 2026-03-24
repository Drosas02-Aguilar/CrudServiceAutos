package com.crudAutos.Autos.Service;

import com.crudAutos.Autos.DAO.IAgencia;
import com.crudAutos.Autos.Entity.Agencia;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AgenciaService {

    @Autowired
    private IAgencia iAgencia;

    public List<Agencia> ConsultarAgencias() {
        return iAgencia.findAll();
    }

    public Agencia GetAgenciaByid(int idagencia) {
        Optional<Agencia> agencia = iAgencia.findById(idagencia);
        return agencia.isPresent() ? agencia.get() : null;
    }

    public List<Agencia> ConsultarAgenciaPorCiudad(String ciudad) {
        return iAgencia.findByCiudadIgnoreCase(ciudad);
    }

    @Transactional
    public Agencia CrearAgencia(Agencia agencia) {
        return iAgencia.save(agencia);
    }
    
    @Transactional
    public Agencia ActualizarAgencia(int idagencia, Agencia agenciaupdate){
    Optional<Agencia> agenciaOptional = iAgencia.findById(idagencia);
    
    if(agenciaOptional.isPresent()){
        Agencia agencia = agenciaOptional.get();
        agencia.setNombre(agenciaupdate.getNombre());
        agencia.setDireccion(agenciaupdate.getDireccion());
        agencia.setTelefono(agenciaupdate.getTelefono());
        agencia.setCiudad(agenciaupdate.getCiudad());
        return iAgencia.save(agencia);
    }
    return null;
    
    }
    
    @Transactional
    public Agencia EliminarAgencia(int idagencia){
    Optional<Agencia> agenciaOptional = iAgencia.findById(idagencia);
    
    if(agenciaOptional.isPresent()){
    Agencia agencia = agenciaOptional.get();
    iAgencia.delete(agencia);
    return agencia;
    }
    
    return null;
    
    }
    
}
