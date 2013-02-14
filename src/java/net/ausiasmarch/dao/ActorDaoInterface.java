/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.dao;

import java.util.List;
import net.ausiasmarch.pojo.Actor;
import org.hibernate.HibernateException;

/**
 *
 * @author María Galbis
 */
public interface ActorDaoInterface extends GenericDao<Actor> {
    
    public Actor readInfo(Actor entity) throws HibernateException;
    
    public List<Actor> readAllInfo() throws HibernateException;
}
