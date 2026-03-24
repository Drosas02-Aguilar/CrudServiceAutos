
package com.crudAutos.Autos.RestController;

import com.crudAutos.Autos.Entity.Agencia;
import com.crudAutos.Autos.Entity.Result;
import com.crudAutos.Autos.Service.AgenciaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/agencias")
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @PostMapping
    public ResponseEntity<?> CrearAgencia(@RequestBody Agencia agencia) {
        Result serviceResult = new Result();
        try {
            Agencia nueva = agenciaService.CrearAgencia(agencia);
            serviceResult.status = 201;
            serviceResult.ErrorMessage = "Agencia creada con exito";
            serviceResult.object = nueva;
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @GetMapping
    public ResponseEntity<?> ConsultarAgencias() {
        Result serviceResult = new Result();
        try {
            List<Agencia> agencias = agenciaService.ConsultarAgencias();
            if (agencias.isEmpty()) {
                serviceResult.status = 204;
                serviceResult.ErrorMessage = "No existen agencias registradas";
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Agencias encontradas";
                serviceResult.object = agencias;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @GetMapping("/{idagencia}")
    public ResponseEntity<?> GetAgenciaById(@PathVariable int idagencia) {
        Result serviceResult = new Result();
        try {
            Agencia agencia = agenciaService.GetAgenciaByid(idagencia);
            if (agencia == null) {
                serviceResult.status = 404;
                serviceResult.ErrorMessage = "Agencia no encontrada";
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Agencia encontrada";
                serviceResult.object = agencia;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<?> ConsultarAgenciaPorCiudad(@PathVariable String ciudad) {
        Result serviceResult = new Result();
        try {
            List<Agencia> agencias = agenciaService.ConsultarAgenciaPorCiudad(ciudad);
            if (agencias.isEmpty()) {
                serviceResult.status = 204;
                serviceResult.ErrorMessage = "No se encontraron agencias en la ciudad: " + ciudad;
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Agencias encontradas";
                serviceResult.object = agencias;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @PutMapping("/{idagencia}")
    public ResponseEntity<?> EditarAgencia(@PathVariable int idagencia, @RequestBody Agencia agenciaUpdate) {
        Result serviceResult = new Result();
        try {
            Agencia actualizada = agenciaService.ActualizarAgencia(idagencia, agenciaUpdate);
            if (actualizada == null) {
                serviceResult.status = 404;
                serviceResult.ErrorMessage = "Agencia no encontrada";
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Agencia actualizada con exito";
                serviceResult.object = actualizada;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @DeleteMapping("/{idagencia}")
    public ResponseEntity<?> EliminarAgencia(@PathVariable int idagencia) {
        Result serviceResult = new Result();
        try {
            Agencia eliminada = agenciaService.EliminarAgencia(idagencia);
            if (eliminada == null) {
                serviceResult.status = 404;
                serviceResult.ErrorMessage = "Agencia no encontrada";
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Agencia eliminada correctamente";
                serviceResult.object = eliminada;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }
}
