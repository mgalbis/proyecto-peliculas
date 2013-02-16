/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.utilities;

import java.util.Iterator;
import java.util.Set;
import net.ausiasmarch.pojo.Generic;

/**
 * Funciones para colecciones
 * @author María Galbis
 */
public class Collections {

    public static Set removeItem(Set set, Generic o){
        for(Iterator it = set.iterator(); it.hasNext();){
            Object element = it.next();
            
            if (((Generic) element).getId().intValue() == o.getId().intValue()) {
                it.remove();
            }
        }
        return set;
    }
}
