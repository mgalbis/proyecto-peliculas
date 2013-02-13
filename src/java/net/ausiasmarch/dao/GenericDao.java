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
public interface GenericDao<objeto> {
    
    public long create(objeto entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException ;

    public void update(objeto entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException;

    public void delete(objeto entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException;

    public objeto read(objeto entity) throws HibernateException;
    
    public List<objeto> readAll() throws HibernateException;
    
    public int count() throws HibernateException;
    
    public int getPages(int pageSize) throws HibernateException;
    
    public List<objeto> getPage(int pageSize, int pageNumber) throws HibernateException;
}
