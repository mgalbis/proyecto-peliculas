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
import net.ausiasmarch.pojo.HibernateUtil;
import net.ausiasmarch.pojo.Pelicula;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;

/**
 *
 * @author María Galbis
 */
public class PeliculaDao implements PeliculaDaoInterface {

    private Session sesion;
    private Transaction tx;

    @Override
    public Integer create(Pelicula entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        Serializable id = 0;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx =  sesion.beginTransaction();
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
    public void update(Pelicula entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
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
    public void delete(Pelicula entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
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
    public Pelicula read(Pelicula entity) throws HibernateException {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            entity = (Pelicula) sesion.get(Pelicula.class, entity.getId());
            Hibernate.initialize(entity.getActors());
            Hibernate.initialize(entity.getGenero());
            Hibernate.initialize(entity.getDirector());
        } catch (HibernateException he) {
            throw new HibernateException("Error en read DAO", he);
        } finally {
            sesion.close();
        }
        return entity;
    }
    
    @Override
    public Pelicula readInfo(Pelicula entity) throws HibernateException {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            entity = (Pelicula) sesion.get(Pelicula.class, entity.getId());
        } catch (HibernateException he) {
            throw new HibernateException("Error en read DAO", he);
        } finally {
            sesion.close();
        }
        return entity;
    }

    @Override
    public List<Pelicula> readAll() throws HibernateException {
        List<Pelicula> lista = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            lista = sesion.createQuery("from Pelicula").list();
        } catch (HibernateException he) {
            throw new HibernateException("Error en readAll DAO", he);
        } finally {
            sesion.close();
        }
        return lista;
    }
    
    @Override
    public List<Pelicula> readAllInfo() throws HibernateException {
        List<Pelicula> lista = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            lista = sesion.createQuery("from Pelicula").list();
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
            Criteria criteria = sesion.createCriteria(Pelicula.class);
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
    public List<Pelicula> getPage(int pageSize, int pageNumber) throws HibernateException {
        List<Pelicula> clientes;
        sesion = HibernateUtil.getSessionFactory().openSession();
        try {           
            Criteria criteria = sesion.createCriteria(Pelicula.class);
            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            clientes = (List<Pelicula>) criteria.list();
        } catch (HibernateException he) {
            throw new HibernateException("Error en getPage DAO", he);           
        } finally {
            sesion.close();
        }
        return clientes;
    }
    
}
