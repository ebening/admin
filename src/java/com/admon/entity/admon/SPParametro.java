package com.admon.entity.admon;

public class SPParametro {

    private String parametroNombre;
    private Object parametroValor;
    private Class clazz;

    public SPParametro() {
    }

    public SPParametro(String parametroNombre, Object parametroValor) {
        this.parametroNombre = parametroNombre;
        this.parametroValor = parametroValor;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getParametroNombre() {
        return parametroNombre;
    }

    public void setParametroNombre(String parametroNombre) {
        this.parametroNombre = parametroNombre;
    }

    public Object getParametroValor() {
        return parametroValor;
    }

    public void setParametroValor(Object parametroValor) {
        this.parametroValor = parametroValor;
    }

}
