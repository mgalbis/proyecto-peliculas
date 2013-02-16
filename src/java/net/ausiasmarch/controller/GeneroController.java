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
import net.ausiasmarch.dao.GeneroDao;
import net.ausiasmarch.pojo.Genero;
import net.ausiasmarch.utilities.GeneroJsonAdapter;
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
@RequestMapping({"/generos"})
public class GeneroController {
    
    @Autowired
    GeneroDao dao;
 
    
    @RequestMapping({"index.html"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {      
        return new ModelAndView("index", "contenido", "generosList.jsp");
    }
    
    @RequestMapping({"list.json"})
    public ModelAndView generos(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        List<Genero> generos = dao.readAll();
        String data = GeneroJsonAdapter.toJson(generos);
        
        return new ModelAndView("listJson", "data", data);
    }
    
    @RequestMapping({"single.json"})
    public ModelAndView genero(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Genero genero = new Genero();;
        String data = "";
        if(request.getParameter("id") != null){
            genero.setId(Integer.parseInt(request.getParameter("id")));
            genero = dao.read(genero);
           
            data = GeneroJsonAdapter.toJson(genero);
        }
        
        return new ModelAndView("singleJson", "data", data);
    }
    
    
    @RequestMapping({"form.html"})
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "generosForm.jsp");
        
        int id = 0;

        if(request.getParameter("id") != null){    
            id = Integer.parseInt(request.getParameter("id"));
        }
        
         model.addObject("id", id);
        return model;
    }
    
    @RequestMapping({"view.html"})
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "generoView.jsp");
        
        if(request.getParameter("id") != null){
            Genero genero = new Genero();
            genero.setId(Integer.parseInt(request.getParameter("id")));
            model.addObject("genero", dao.read(genero));
        }
        
        return model;
    }
    
    @RequestMapping({"save.html"})
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        
        Genero genero = new Gson().fromJson(request.getParameter("form"), Genero.class);
        
        if(genero.getId() == null){
            dao.create(genero);
        } else {
            dao.update(genero);
        }
        
        return new ModelAndView("index", "contenido", "generosList.jsp");
    }
    
    @RequestMapping({"create.html"})
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        
        Genero genero = new Gson().fromJson(request.getParameter("form"), Genero.class);
        dao.create(genero);
        
        return new ModelAndView("index", "contenido", "generoList.jsp");
    }
    
    @RequestMapping({"delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

  
            if(request.getParameter("id") != null){
            Genero genero = new Genero();
            genero.setId(Integer.parseInt(request.getParameter("id")));
            dao.delete(genero);
        }
  
        
        return new ModelAndView("index", "contenido", "generosList.jsp");
    }
}
