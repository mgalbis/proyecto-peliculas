/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.util.List;
import net.ausiasmarch.pojo.Director;
import net.ausiasmarch.pojo.HibernateUtil;
import net.ausiasmarch.pojo.Pelicula;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author María Galbis
 */
public class DirectorDao extends GenericDaoImp<Director> {

    
    @Override
    public Director read(Integer id) throws HibernateException {
        Director entity = new Director();
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            entity = (Director) sesion.get(Director.class, id);
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
    public List<Director> getPage(int pageSize, int pageNumber, String param) throws HibernateException {
        List<Director> lista;
        sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sesion.createCriteria(Director.class);
            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            
            if(param != null){
                Criterion id = Restrictions.eq("id", param);
                Criterion nombre = Restrictions.like("nombre", "%"+param+"%");
                
                criteria.add(nombre);
            }
            
            lista = (List<Director>) criteria.list();
            
            for(Director d : lista){
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
