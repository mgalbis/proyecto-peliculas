package net.ausiasmarch.pojo;
// Generated 14-feb-2013 15:47:47 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Director generated by hbm2java
 */
public class Director  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private Set peliculas = new HashSet(0);

    public Director() {
    }

	
    public Director(String nombre) {
        this.nombre = nombre;
    }
    public Director(String nombre, Set peliculas) {
       this.nombre = nombre;
       this.peliculas = peliculas;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getPeliculas() {
        return this.peliculas;
    }
    
    public void setPeliculas(Set peliculas) {
        this.peliculas = peliculas;
    }




}


