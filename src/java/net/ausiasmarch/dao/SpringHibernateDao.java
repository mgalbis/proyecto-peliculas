/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author María Galbis
 */
//@Repository
public class SpringHibernateDao extends HibernateDaoSupport implements GenericDao {

//    @Autowired
//    public SpringHibernateDao(SessionFactory sessionFactory) {
//        super.setSessionFactory(sessionFactory);
//    }

    @Override
    public Integer create(Object entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Object entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Object entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object read(Object entity) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List readAll() throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int count() throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getPages(int pageSize) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List getPage(int pageSize, int pageNumber) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
