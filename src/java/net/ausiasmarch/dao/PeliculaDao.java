/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.util.List;
import net.ausiasmarch.pojo.HibernateUtil;
import net.ausiasmarch.pojo.Pelicula;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

/**
 *
 * @author María Galbis
 */
public class PeliculaDao extends GenericDaoImp<Pelicula> {

    @Override
    public Pelicula read(Integer id) throws HibernateException {
        Pelicula entity = new Pelicula();
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            entity = (Pelicula) sesion.get(Pelicula.class, id);
            if (entity != null) {
                Hibernate.initialize(entity.getActores());
                Hibernate.initialize(entity.getGenero());
                Hibernate.initialize(entity.getDirector());
            }
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

            for (Pelicula p : lista) {
                Hibernate.initialize(p.getActores());
                Hibernate.initialize(p.getGenero());
                Hibernate.initialize(p.getDirector());
            }

            sesion.flush();
        } catch (HibernateException he) {
            throw new HibernateException("Error en readAll DAO", he);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public List<Pelicula> getPage(int pageSize, int pageNumber) throws HibernateException {
        List<Pelicula> lista;
        sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sesion.createCriteria(Pelicula.class);
            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            lista = (List<Pelicula>) criteria.list();

            for (Pelicula p : lista) {
                Hibernate.initialize(p.getActores());
                Hibernate.initialize(p.getGenero());
                Hibernate.initialize(p.getDirector());
            }
        } catch (HibernateException he) {
            throw new HibernateException("Error en getPage DAO", he);
        } finally {
            if(sesion.isOpen()){
                sesion.close();
            }
        }
        return lista;
    }
}
