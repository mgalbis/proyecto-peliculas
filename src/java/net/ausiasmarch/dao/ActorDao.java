/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

/**
 *
 * @author María Galbis
 */
public class ActorDao implements ActorDaoInterface {
    
    private Session sesion;
    private Transaction tx;

    @Override
    public long create(Actor entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        long id = 0;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = (Transaction) sesion.beginTransaction();
            id = (Long) sesion.save(entity);
                tx.commit();
        } catch (javax.transaction.RollbackException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HibernateException he) {
            tx.rollback();
            throw new HibernateException("Error en create DAO", he);
        } finally {
            sesion.close();
        }
        return id;
    }

    @Override
    public void update(Actor entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = (Transaction) sesion.beginTransaction();
            sesion.update(entity);
            tx.commit();
        } catch (javax.transaction.RollbackException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HibernateException he) {
            tx.rollback();
            throw new HibernateException("Error en update DAO", he);
        } finally {
            sesion.close();
        }
    }

    @Override
    public void delete(Actor entity) throws HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            tx = (Transaction) sesion.beginTransaction();
            sesion.delete(entity);
            tx.commit();
        } catch (javax.transaction.RollbackException ex) {
            Logger.getLogger(ActorDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HibernateException he) {
            tx.rollback();
            throw new HibernateException("Error en delete DAO", he);
        } finally {
            sesion.close();
        }
    }

    @Override
    public Actor read(Actor entity) throws HibernateException {
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            entity = (Actor) sesion.get(Actor.class, entity.getId());
        } catch (HibernateException he) {
            throw new HibernateException("Error en read DAO", he);
        } finally {
            sesion.close();
        }
        return entity;
    }

    @Override
    public List<Actor> readAll() throws HibernateException {
        List<Actor> lista = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            lista = sesion.createQuery("from Actor").list();
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
            Criteria criteria = sesion.createCriteria(Actor.class);
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
    public List<Actor> getPage(int pageSize, int pageNumber) throws HibernateException {
        List<Actor> clientes;
        sesion = HibernateUtil.getSessionFactory().openSession();
        try {           
            Criteria criteria = sesion.createCriteria(Actor.class);
            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            clientes = (List<Actor>) criteria.list();
        } catch (HibernateException he) {
            throw new HibernateException("Error en getPage DAO", he);           
        } finally {
            sesion.close();
        }
        return clientes;
    }
    
}
