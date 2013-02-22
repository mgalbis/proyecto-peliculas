/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.utilities;

import java.util.Collection;

/**
 * Funciones para colecciones
 * @author María Galbis
 */
public class Collections {

//    public static Collection removeItem(Collection set, GenericPojo o){
//        for(Iterator it = set.iterator(); it.hasNext();){
//            Object element = it.next();
//            
//            if (((GenericPojo) element).getId().intValue() == o.getId().intValue()) {
//                it.remove();
//            }
//        }
//        return set;
//    }
    
    public static Class toCollectionClass(Class c) {
        if(Collection.class.isAssignableFrom(c)){
            return Collection.class;
        }
         return c;
    }
}
