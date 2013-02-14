/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.util.List;
import net.ausiasmarch.pojo.Pelicula;
import org.hibernate.HibernateException;

/**
 *
 * @author María Galbis
 */
public interface PeliculaDaoInterface extends GenericDao<Pelicula>{
    
    public Pelicula readInfo(Pelicula entity) throws HibernateException;
    
    public List<Pelicula> readAllInfo() throws HibernateException;
    
}
