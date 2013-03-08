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
import net.ausiasmarch.json.ActorJsonForm;
import net.ausiasmarch.pojo.Genero;
import net.ausiasmarch.json.GeneroJsonData;
import net.ausiasmarch.json.GeneroJsonForm;
import net.ausiasmarch.pojo.Actor;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
 
    @RequestMapping({""})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {      
        ModelAndView mod = new ModelAndView("index", "contenido", "table.jsp");
        mod.addObject("table", "Géneros");
        return mod;
    }
    
    @RequestMapping({"all/json"})
    public ModelAndView generos(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        List<Genero> generos = dao.readAll();
        String data = GeneroJsonData.toJson(generos);
        
        return new ModelAndView("listJson", "data", data);
    }
    
    @RequestMapping({"{limit}/{page}/{search}/json"})
    public ModelAndView getPage(@PathVariable Integer limit, @PathVariable Integer page, @PathVariable String search, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        search = search.equals("null") ? null : search;
        
        List<Genero> generos = dao.getPage(limit, page, search);
        String data = GeneroJsonData.toJson(generos);

        return new ModelAndView("listJson", "data", data);
    }
    
    @RequestMapping({"pages/{limit}/{search}"})
    public ModelAndView getPages(@PathVariable Integer limit, @PathVariable String search, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        search = search.equals("null") ? null : search;
        
        int pages = dao.getPages(limit, search);
        return new ModelAndView("singleJson", "data", "{\"pages\":"+pages+"}");
    }
    
    @RequestMapping(value = "{id}/json")
    public ModelAndView genero(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Genero genero = dao.read(id);
           
        String data = GeneroJsonData.toJson(genero);

        return new ModelAndView("singleJson", "data", data);
    }
    
    @RequestMapping(value = "form/json")
    public ModelAndView formJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        return new ModelAndView("singleJson", "data",  GeneroJsonForm.toJson(new Genero()));
    }
    
    
    @RequestMapping(value = "new/form")
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("table", "Géneros");
        return model;
    }
    
    @RequestMapping(value = "{id}/form")
    public ModelAndView form(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("id", id);
        model.addObject("table", "Géneros");
        return model;
    }
    
    
    @RequestMapping(value = "{type}/modalList")
    public ModelAndView modalList(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("list");
        model.addObject("table", "generos");
        model.addObject("type", type);
        return model;
    }
    
    @RequestMapping(value = "{id}/view")
    public ModelAndView view(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "view.jsp");
        model.addObject("id", id);
        model.addObject("table", "Géneros");
        return model;
    }
    
    @RequestMapping({"{form}/save"})
    public void save(@PathVariable String form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        
        Genero genero = GeneroJsonData.fromJson(form);
        
        if(genero.getId() == null){
            dao.create(genero);
        } else {
            dao.update(genero);
        }
        
    }
    
    @RequestMapping({"{id}/delete"})
    public void delete(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

            Genero genero = new Genero();
            genero.setId(id);
            dao.delete(genero);
  
    }
}
