/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.HibernateUtil;
import net.ausiasmarch.pojo.Pelicula;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author María Galbis
 */
public class ActorDao extends GenericDaoImp<Actor> {
    
    
    @Override
    public Actor read(Integer id) throws HibernateException {
        Actor entity = new Actor();
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            entity = (Actor) sesion.get(Actor.class, id);
            if(entity != null){
                Hibernate.initialize(entity.getPeliculas());
            }
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
            for(Actor d : lista){
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
    public int count(String param) throws HibernateException {
        int cantidad;
        
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = sesion.createCriteria(Actor.class);
            
            if(param != null){
                Criterion id = Restrictions.eq("id", param);
                Criterion nombre = Restrictions.like("nombre", "%"+param+"%");
                
                criteria.add(nombre);
            }
            
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
    public List<Actor> getPage(int pageSize, int pageNumber, String param) throws HibernateException {
        List<Actor> lista;
        sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sesion.createCriteria(Actor.class);
            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            
            if(param != null){
                Criterion id = Restrictions.eq("id", param);
                Criterion nombre = Restrictions.like("nombre", "%"+param+"%");
                
                criteria.add(nombre);
            }
            
            lista = (List<Actor>) criteria.list();
            
            for(Actor d : lista){
                Hibernate.initialize(d.getPeliculas());
            }
        } catch (HibernateException he) {
            throw new HibernateException("Error en getPage DAO", he);
        } finally {
            sesion.close();
        }
        return lista;
    }
}
