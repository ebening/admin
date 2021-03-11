package com.admon.dao;

import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseDAO extends HibernateDaoSupport {

    public BaseDAO() {
    }

    public ResourceBundle errors = ResourceBundle.getBundle("com.admon.dao.resources.ErrorsDAO");
    public Logger log = Logger.getLogger(this.getClass());
}
