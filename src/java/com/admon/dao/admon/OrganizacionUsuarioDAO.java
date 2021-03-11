package com.admon.dao.admon;

import com.admon.dao.GenericDAOImpl;
import com.admon.entity.admon.OrganizacionUsuario;

public class OrganizacionUsuarioDAO extends GenericDAOImpl<OrganizacionUsuario, Integer> {

    /*
 * Método para tipar el objeto genérico. Es utilizado
 * en la implementación del GenericDAO para identificar
 * el objeto genérico la cual es necesaria para realizar
 * las consultas a la BD.
 * @return Regresa un objeto <b>Class</b> que identifica el tipo genérico. */
    @Override
    protected Class<OrganizacionUsuario> getEntityClass() {
        return OrganizacionUsuario.class;
    }
}
