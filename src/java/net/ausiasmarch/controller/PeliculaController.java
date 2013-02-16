/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.controller;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import net.ausiasmarch.dao.PeliculaDao;
import net.ausiasmarch.pojo.Pelicula;
import net.ausiasmarch.json.PeliculaJsonAdapter;
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
@RequestMapping({"/peliculas"})
public class PeliculaController {
    
    @Autowired
    PeliculaDao dao;
 
    
    @RequestMapping({"index.html"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {      
        return new ModelAndView("index", "contenido", "peliculasList.jsp");
    }
    
    @RequestMapping({"list.json"})
    public ModelAndView peliculas(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        List<Pelicula> peliculas = dao.readAll();
        String data = PeliculaJsonAdapter.toJson(peliculas);
        
        return new ModelAndView("listJson", "data", data);
    }
    
    @RequestMapping({"single.json"})
    public ModelAndView pelicula(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Pelicula pelicula = new Pelicula();
        String data = "";
        
        if(request.getParameter("id") != null){
            pelicula.setId(Integer.parseInt(request.getParameter("id")));
            pelicula = dao.read(pelicula);
            
            data = PeliculaJsonAdapter.toJson(pelicula);
        }
        
        return new ModelAndView("singleJson", "data", data);
    }
    
    @RequestMapping({"form.html"})
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "peliculaForm.jsp");
        
        int id = 0;

        if(request.getParameter("id") != null){    
            id = Integer.parseInt(request.getParameter("id"));
        }
        
         model.addObject("id", id);
        return model;
    }
    
    @RequestMapping({"view.html"})
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "peliculaView.jsp");
        
        if(request.getParameter("id") != null){
            Pelicula pelicula = new Pelicula();
            pelicula.setId(Integer.parseInt(request.getParameter("id")));
            model.addObject("pelicula", dao.read(pelicula));
        }
        
        return model;
    }
    
    @RequestMapping({"save.html"})
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        
        Pelicula pelicula = new Gson().fromJson(request.getParameter("form"), Pelicula.class);
        
        if(pelicula.getId() == null){
            dao.create(pelicula);
        } else {
            dao.update(pelicula);
        }
        
        return new ModelAndView("index", "contenido", "peliculasList.jsp");
    }
    
    @RequestMapping({"create.html"})
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        
        Pelicula pelicula = new Gson().fromJson(request.getParameter("form"), Pelicula.class);
        dao.create(pelicula);
        
        return new ModelAndView("index", "contenido", "peliculaList.jsp");
    }
    
    @RequestMapping({"delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

  
            if(request.getParameter("id") != null){
            Pelicula pelicula = new Pelicula();
            pelicula.setId(Integer.parseInt(request.getParameter("id")));
            dao.delete(pelicula);
        }
  
        
        return new ModelAndView("index", "contenido", "peliculasList.jsp");
    }
    
}
