package com.admon.model.admon;

import com.admon.bss.admon.LenguajeBss;
import com.admon.model.BaseModel;

public class LenguajeDWR extends BaseModel {

    /*
     * Inyección de dependencias con Spring. Estas dependencias se
     * configuran en el applicationContext.xml, y además necesitan un
     * método setter por cada variable que se anexa al final de la clase.
     */
    private LenguajeBss lenguajeBss;

    public LenguajeDWR() {
    }

    /*
     * Inyección de dependencias con Spring, cada
     * referencia definida al inicio de la clase requiere un método
     * Setter.
     */
    public void setLenguajeBss(LenguajeBss lenguajeBss) {
        this.lenguajeBss = lenguajeBss;
    }
}
