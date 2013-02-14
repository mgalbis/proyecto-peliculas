/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.controller;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import net.ausiasmarch.dao.ActorDaoInterface;
import net.ausiasmarch.dao.DirectorDaoInterface;
import net.ausiasmarch.dao.GeneroDaoInterface;
import net.ausiasmarch.dao.PeliculaDaoInterface;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.Pelicula;
import net.ausiasmarch.utilities.Collections;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author María Galbis
 */
@Controller
@RequestMapping({"/pelicula"})
public class PeliculaController {
    
    @Autowired
    PeliculaDaoInterface peliculaDao;
    
    @Autowired
    ActorDaoInterface actorDao;
    
    @Autowired
    GeneroDaoInterface generoDao;
    
    @Autowired
    DirectorDaoInterface directorDao;
 
    
    @RequestMapping({"index.html"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        return new ModelAndView("index", "contenido", "peliculasList.jsp");
    }
    
    @RequestMapping({"list.json"})
    public ModelAndView peliculas(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//        Pelicula p = new Pelicula();
//        p.setId(4);
        Gson gson = new Gson();
        //String pe = gson.toJson(peliculaDao.read(p));
        
//        System.out.println(((Pelicula) peliculaDao.read(p)).getActores());
//        
//        for(Actor a : (Set<Actor>) ((Pelicula) peliculaDao.read(p)).getActors()){
//            System.out.println(a.getNombre());
//        }
//        Actor a1 = new Actor();
//        Actor a2 = new Actor();
//        
//        a1.setNombre("Elijah Wood");
//        a2.setNombre("Liz Taylor");
//        
//        Director d = new Director();
//        d.setNombre("Peter Jackson");
//        
//        Integer l1 = null;
//        Integer l2 = null;
//        Integer l3 = null;
//        try {
//            l1 = actorDao.create(a1);
//            l2 = actorDao.create(a2);
//            l3 = directorDao.create(d);
//        } catch (HibernateException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (RollbackException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (HeuristicMixedException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (HeuristicRollbackException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SecurityException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalStateException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SystemException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        
//        
//        Set s = new HashSet();
//        
//        Actor act1 = new Actor();
//        Actor act2 = new Actor();
//        
//        act1.setId(l1);
//        act2.setId(l2);
//
//        act1 = actorDao.read(act1);
//        act2 = actorDao.read(act2);
//        
//        System.out.println(act1.getNombre()+", "+act2.getNombre());
//        s.add(act1);
//        s.add(act2);
//        
//        Genero g = new Genero();
//        g.setId(2);
//        Director dir = new Director();
//        dir.setId(l3);
//        
//        g.setId(1);
//        
//        p.setTitulo("El Señor De Los Anillos");
//        p.setVo("The Lord Of The Rings");
//        p.setDescripcion("Una descripción. Bla bla bla...");
//        p.setFecha(new Date());
//        p.setCalificacion(13);
//        p.setDuracion(145);
//        p.setGenero(generoDao.read(g));
//        p.setActors(s);
//        p.setDirector(directorDao.read(dir));
//        Integer l = null;
//        try {
//            l = peliculaDao.create(p);
//        } catch (HibernateException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (RollbackException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (HeuristicMixedException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (HeuristicRollbackException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SecurityException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalStateException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SystemException ex) {
//            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Actor act = new Actor();
        act.setId(30);
        act = actorDao.read(act);
        
        Pelicula peli = new Pelicula();
        peli.setId(6);
        
        peli = peliculaDao.read(peli);
        
//        for(Iterator it = peli.getActors().iterator(); it.hasNext();){
//            Object element = it.next();
//            System.out.print(((Actor) element).getId().intValue() == act.getId().intValue());
//            if (((Actor) element).getId().intValue() == act.getId().intValue()) {
//                it.remove();
//            }
//        }
        
        Collections.removeItem(peli.getActors(), act);
        
        
        System.out.println(peli.getActors().size());
        try {
            peliculaDao.update(peli);
    //        Actor actor = new Actor();
    //        actor.setId(16);
    //        
    //        for(Pelicula p : (Set<Pelicula>) actorDao.read(actor).getPeliculas()){
    //            System.out.println(p.getTitulo());
    //        }
    //        
        } catch (HibernateException ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(PeliculaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Actor actor = new Actor();
            actor.setId(16);
            
        for(Actor p : (Set<Actor>) peliculaDao.read(peli).getActors()){
                System.out.println(p.getNombre());
            }
//        System.out.println(peliculaDao.read(peli).getTitulo());
        return new ModelAndView("index");
    }
    
    @RequestMapping({"single.json"})
    public ModelAndView pelicula(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Pelicula p = new Pelicula();
        p.setId(4);
        return new ModelAndView("peliculaJson", "pelicula", peliculaDao.read(p));
    }
    
    @RequestMapping({"form.html"})
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Pelicula p = new Pelicula();
        p.setId(4);
        return new ModelAndView("peliculaJson", "pelicula", peliculaDao.read(p));
    }
    
    @RequestMapping({"view.html"})
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Pelicula p = new Pelicula();
        p.setId(4);
        return new ModelAndView("peliculaJson", "pelicula", peliculaDao.read(p));
    }
    
    @RequestMapping({"update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Pelicula p = new Pelicula();
        p.setId(4);
        return new ModelAndView("peliculaJson", "pelicula", peliculaDao.read(p));
    }
    
    @RequestMapping({"create.html"})
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Pelicula p = new Pelicula();
        p.setId(4);
        return new ModelAndView("peliculaJson", "pelicula", peliculaDao.read(p));
    }
    
    @RequestMapping({"delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Pelicula p = new Pelicula();
        p.setId(4);
        return new ModelAndView("peliculaJson", "pelicula", peliculaDao.read(p));
    }
    
}
