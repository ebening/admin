/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admon.dao.admon;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.SeguridadRol;



public class SeguridadRolDAO extends GenericDAOImpl <SeguridadRol,Integer>{
       /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<SeguridadRol> getEntityClass() {
        return SeguridadRol.class;
    }
    
    
}
