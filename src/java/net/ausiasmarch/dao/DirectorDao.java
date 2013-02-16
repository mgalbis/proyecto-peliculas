/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import org.hibernate.Transaction;
import net.ausiasmarch.pojo.Director;
import net.ausiasmarch.pojo.HibernateUtil;
import net.ausiasmarch.pojo.Pelicula;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

/**
 *
 * @author María Galbis
 */
public class DirectorDao implements DirectorDaoInterface {

    private Session sesion;
    private Transaction tx;

    @Override
    public Integer create(Director entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        Serializable id = 0;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
            id = sesion.save(entity);
                tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            throw new HibernateException("Error en create DAO", he);
        } finally {
            sesion.close();
        }
        return (Integer) id;
    }

    @Override
    public void update(Director entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
            sesion.update(entity);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            throw new HibernateException("Error en update DAO", he);
        } finally {
            sesion.close();
        }
    }

    @Override
    public void delete(Director entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
            sesion.delete(entity);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            throw new HibernateException("Error en delete DAO", he);
        } finally {
            sesion.close();
        }
    }

    @Override
    public Director read(Director entity) throws HibernateException {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            entity = (Director) sesion.get(Director.class, entity.getId());
            Hibernate.initialize(entity.getPeliculas());
        } catch (HibernateException he) {
            throw new HibernateException("Error en read DAO", he);
        } finally {
            sesion.close();
        }
        return entity;
    }

    @Override
    public List<Director> readAll() throws HibernateException {
        List<Director> lista = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            lista = sesion.createQuery("from Director").list();
            for(Director d : lista){
                Hibernate.initialize(d.getPeliculas());
            }
            
        } catch (HibernateException he) {
            throw new HibernateException("Error en readAll DAO", he);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public int count() throws HibernateException {
        int cantidad;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = sesion.createCriteria(Director.class);
            criteria.setProjection(Projections.rowCount());
            cantidad = (Integer) criteria.list().get(0);
        } catch (HibernateException he) {
            throw new HibernateException("Error en countAll DAO", he);
        } finally {
            sesion.close();
        }
        return cantidad;
    }

    @Override
    public int getPages(int pageSize) throws HibernateException {
        return (int) Math.ceil(this.count() / pageSize);
    }

    @Override
    public List<Director> getPage(int pageSize, int pageNumber) throws HibernateException {
        List<Director> clientes;
        sesion = HibernateUtil.getSessionFactory().openSession();
        try {           
            Criteria criteria = sesion.createCriteria(Director.class);
            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            clientes = (List<Director>) criteria.list();
        } catch (HibernateException he) {
            throw new HibernateException("Error en getPage DAO", he);           
        } finally {
            sesion.close();
        }
        return clientes;
    }
    
}
