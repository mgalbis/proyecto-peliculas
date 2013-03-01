/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import net.ausiasmarch.pojo.Director;
import org.hibernate.Transaction;
import net.ausiasmarch.pojo.Genero;
import net.ausiasmarch.pojo.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

/**
 *
 * @author María Galbis
 */
public class GeneroDao extends GenericDaoImp<Genero> {
    
    @Override
    public Genero read(Integer id) throws HibernateException {
        Genero entity = new Genero();
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            entity = (Genero) sesion.get(Genero.class, id);
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
    public List<Genero> readAll() throws HibernateException {
        List<Genero> lista = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            lista = sesion.createQuery("from Genero").list();
            
            for(Genero d : lista){
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
