package com.crudAutos.Autos.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "AUTOS")
public class Auto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idauto") 
    private int idauto;
    @Column(name = "marca", nullable = false)
    private String marca;
    @Column(name= "modelo", nullable = false)
    private  String modelo;
    @Column(name="anio", nullable = false)
    private String anio;
    @Column(name="color", nullable = true)
    private String color;
    @Column(name="precio", nullable = true)
    private int precio;
    @Column(name="placa",nullable = true)
    private String placa;
    
    @Lob
    @Column(name="imagen")
    private String imagen;
    
    
    @ManyToOne
    @JoinColumn(name="idagencias", nullable = true)
    @JsonIgnoreProperties("autos")
    private Agencia agencia;

    public int getIdauto() {
        return idauto;
    }

    public void setIdauto(int idauto) {
        this.idauto = idauto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
}
