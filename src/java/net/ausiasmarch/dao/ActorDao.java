/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.util.List;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

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
}
