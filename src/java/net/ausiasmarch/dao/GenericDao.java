/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.util.List;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import org.hibernate.HibernateException;

/**
 *
 * @author María Galbis
 */
public interface GenericDao<T> {
    
    public Integer create(T entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException;

    public void update(T entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException;

    public void delete(T entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException;

    public T read(T entity) throws HibernateException;
    
    public List<T> readAll() throws HibernateException;
    
    public int count() throws HibernateException;
    
    public int getPages(int pageSize) throws HibernateException;
    
    public List<T> getPage(int pageSize, int pageNumber) throws HibernateException;
}
