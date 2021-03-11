package com.admon.entity.reportes;

import java.io.Serializable;

public class Evento implements Serializable {

    private Integer eventoId;
    private String nombre;
    private Integer organizacionId;

    public Evento() {
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreEvento) {
        this.nombre = nombreEvento;
    }

    public Integer getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(Integer organizacionId) {
        this.organizacionId = organizacionId;
    }

}
