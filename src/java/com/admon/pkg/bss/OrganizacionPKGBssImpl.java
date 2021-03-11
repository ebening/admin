package com.admon.pkg.bss;

import com.admon.pkg.dao.OrganizacionPKGDAO;
import com.admon.entity.admon.SPParametro;
import com.admon.pkg.entity.OrganizacionGenerarRS;
import java.util.ArrayList;
import java.util.List;

public class OrganizacionPKGBssImpl implements OrganizacionPKGBss {

    // Spring IoC
    private OrganizacionPKGDAO organizacionPKGDAO;

    public OrganizacionPKGBssImpl() {
    }

    // Spring setter
    public void setOrganizacionPKGDAO(OrganizacionPKGDAO organizacionPKGDAO) {
        this.organizacionPKGDAO = organizacionPKGDAO;
    }

    /**
     * Despliega la organizacion en los diferente ambientes
     *
     * @param organizacionId id de la organización
     * @return resumen
     */
    @Override
    public List<OrganizacionGenerarRS> organizacionGenerarPRC(Integer organizacionId) {
        String spName = "organizacionGenerarPRC";
        List list = null;
        List<SPParametro> spList = new ArrayList();
        try {
            spList.add(new SPParametro("organizacionId", organizacionId));
            list = organizacionPKGDAO.callStoredProcedure(spName, spList);
        } catch (Exception e) {
            System.out.println("error> " + e);
        }
        return list;
    }

}
