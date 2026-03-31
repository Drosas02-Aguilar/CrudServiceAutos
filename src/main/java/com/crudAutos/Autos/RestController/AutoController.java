
package com.crudAutos.Autos.RestController;
import com.crudAutos.Autos.Entity.Auto;
import com.crudAutos.Autos.Entity.Result;
import com.crudAutos.Autos.Service.AutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @PostMapping
    public ResponseEntity<?> CrearAuto(@RequestBody Auto auto) {
        Result serviceResult = new Result();
        try {
            Auto nuevo = autoService.CrearAuto(auto);
            serviceResult.status = 201;
            serviceResult.ErrorMessage = "Auto creado con exito";
            serviceResult.object = nuevo;
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @GetMapping
    public ResponseEntity<?> ConsultarAutos(@RequestParam(defaultValue = "0")int page,
                                            @RequestParam(defaultValue = "6")int size                ) {
        Result serviceResult = new Result();
        try {
            Page<Auto> autos = (Page<Auto>) autoService.ConsultarAutos(page, size);
            if (autos.isEmpty()) {
                serviceResult.status = 204;
                serviceResult.ErrorMessage = "No existen autos registrados";
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Autos encontrados";
                serviceResult.object = autos;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @GetMapping("/{idauto}")
    public ResponseEntity<?> GetAutoById(@PathVariable int idauto) {
        Result serviceResult = new Result();
        try {
            Auto auto = autoService.GetAutoById(idauto);
            if (auto == null) {
                serviceResult.status = 404;
                serviceResult.ErrorMessage = "Auto no encontrado";
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Auto encontrado";
                serviceResult.object = auto;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<?> ConsultarAutosPorMarca(@PathVariable String marca) {
        Result serviceResult = new Result();
        try {
            List<Auto> autos = autoService.ConsultarAutosPorMarca(marca);
            if (autos.isEmpty()) {
                serviceResult.status = 204;
                serviceResult.ErrorMessage = "No se encontraron autos de la marca: " + marca;
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Autos encontrados";
                serviceResult.object = autos;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @GetMapping("/agencia/{idagencia}")
    public ResponseEntity<?> ConsultarAutosPorAgencia(@PathVariable int idagencia) {
        Result serviceResult = new Result();
        try {
            List<Auto> autos = autoService.ConsultarAutosPorAgencia(idagencia);
            if (autos.isEmpty()) {
                serviceResult.status = 204;
                serviceResult.ErrorMessage = "No se encontraron autos para la agencia con id: " + idagencia;
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Autos encontrados";
                serviceResult.object = autos;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @GetMapping("/precio")
    public ResponseEntity<?> ConsultarAutosPorRangoPrecio(@RequestParam Double precioMin, @RequestParam Double precioMax) {
        Result serviceResult = new Result();
        try {                                                                                                                                                                                   
            List<Auto> autos = autoService.ConsultarAutosPorPrecio(precioMin, precioMax);
            if (autos.isEmpty()) {
                serviceResult.status = 204;
                serviceResult.ErrorMessage = "No se encontraron autos en ese rango de precio";
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Autos encontrados";
                serviceResult.object = autos;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @PutMapping("/{idauto}")
    public ResponseEntity<?> EditarAuto(@PathVariable int idauto, @RequestBody Auto autoUpdate) {
        Result serviceResult = new Result();
        try {
            Auto actualizado = autoService.EditarAuto(idauto, autoUpdate);
            if (actualizado == null) {
                serviceResult.status = 404;
                serviceResult.ErrorMessage = "Auto no encontrado";
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Auto actualizado con exito";
                serviceResult.object = actualizado;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @DeleteMapping("/{idauto}")
    public ResponseEntity<?> EliminarAuto(@PathVariable int idauto) {
        Result serviceResult = new Result();
        try {
            Auto eliminado = autoService.ElimianAuto(idauto);
            if (eliminado == null) {
                serviceResult.status = 404;
                serviceResult.ErrorMessage = "Auto no encontrado";
            } else {
                serviceResult.status = 200;
                serviceResult.ErrorMessage = "Auto eliminado correctamente";
                serviceResult.object = eliminado;
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }
}