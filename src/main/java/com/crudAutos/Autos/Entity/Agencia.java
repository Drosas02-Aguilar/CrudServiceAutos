
package com.crudAutos.Autos.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="AGENCIA")
public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idagencia")
    private int idagencia;
    @Column(name="nombre", nullable = false)
    private String nombre;
    @Column(name="direccion", nullable = false)
    private String direccion;
    @Column (name="telefono", nullable = true)
     private String telefono;
    @Column (name="ciudad", nullable = true)
    private String ciudad; 
    
    @OneToMany(mappedBy = "agencia", cascade = CascadeType.ALL)
    @JsonIgnore 
    private List<Auto> autos = new ArrayList<>();

    public int getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(int idagencia) {
        this.idagencia = idagencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }
    

}  
