package com.admon.entity.admon;

import java.io.Serializable;

public class Lenguaje implements Serializable {

    private Integer lenguajeId;
    private String nombre;
    private Integer estatusId;

    public Lenguaje() {
    }

    public Integer getLenguajeId() {
        return lenguajeId;
    }

    public void setLenguajeId(Integer lenguajeId) {
        this.lenguajeId = lenguajeId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

}
