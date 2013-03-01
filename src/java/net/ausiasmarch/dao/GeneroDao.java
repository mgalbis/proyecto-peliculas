/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.util.List;
import net.ausiasmarch.pojo.Genero;
import net.ausiasmarch.pojo.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

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
