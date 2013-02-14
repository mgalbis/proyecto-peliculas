/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.utilities;

import java.util.Iterator;
import java.util.Set;
import net.ausiasmarch.pojo.GenericPojo;

/**
 *
 * @author María Galbis
 */
public class Collections {

    public static Set removeItem(Set set, GenericPojo o){
        for(Iterator it = set.iterator(); it.hasNext();){
            Object element = it.next();
            
            if (((GenericPojo) element).getId().intValue() == o.getId().intValue()) {
                it.remove();
            }
        }
        return set;
    }
}
