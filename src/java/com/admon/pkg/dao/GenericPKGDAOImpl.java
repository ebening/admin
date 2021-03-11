package com.admon.pkg.dao;

import com.admon.dao.BaseDAO;
import com.admon.entity.admon.SPParametro;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public abstract class GenericPKGDAOImpl extends BaseDAO  implements
        GenericPKGDAO {

    @Override
    public List<Object> callStoredProcedure(
            final String spName,
            final List<SPParametro> listaParametros) {
        return (List<Object>) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(final Session session) throws
                    HibernateException,
                    SQLException {
                Query query = session.getNamedQuery(spName);
                for (SPParametro param : listaParametros) {
                    query.setParameter(
                            param.getParametroNombre(),
                            param.getParametroValor());
                }
                return query.list();
            }
        });
    }
}
