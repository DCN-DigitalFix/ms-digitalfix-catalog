package cl.duoc.ms_digitalfix_catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private double tarifa;
    private boolean activo = true;


    public Servicio() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public double getTarifa() {
        return tarifa;
    }


    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }


    public boolean isActivo() {
        return activo;
    }


    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    




}
