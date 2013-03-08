/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import net.ausiasmarch.dao.ActorDao;
import net.ausiasmarch.json.ActorJsonData;
import net.ausiasmarch.json.ActorJsonForm;
import net.ausiasmarch.json.JsonForm;
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
@RequestMapping({"/actores"})
public class ActorController {
    
    @Autowired
    ActorDao dao;
    
    @RequestMapping({""})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {      
        ModelAndView mod = new ModelAndView("index", "contenido", "table.jsp");
        mod.addObject("table", "Actores");
        return mod;
    }
    
    @RequestMapping({"all/json"})
    public ModelAndView generos(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        List<Actor> actores = dao.readAll();
        String data = ActorJsonData.toJson(actores);
        
        return new ModelAndView("listJson", "data", data);
    }
    
    @RequestMapping({"{limit}/{page}/{search}/json"})
    public ModelAndView getPage(@PathVariable Integer limit, @PathVariable Integer page, @PathVariable String search, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        search = search.equals("null") ? null : search;
        
        List<Actor> actores = dao.getPage(limit, page, search);
        String data = ActorJsonData.toJson(actores);

        return new ModelAndView("listJson", "data", data);
    }
    
    @RequestMapping({"pages/{limit}/{search}"})
    public ModelAndView getPages(@PathVariable Integer limit, @PathVariable String search, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        search = search.equals("null") ? null : search;
        int pages = dao.getPages(limit, search);
        
        String data = "{\"pages\":"+pages+"}";

        return new ModelAndView("singleJson", "data", data);
    }
    
    @RequestMapping(value = "{id}/json")
    public ModelAndView genero(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Actor actor = dao.read(id);
           
        String data = ActorJsonData.toJson(actor);
       
        return new ModelAndView("singleJson", "data", data);
    }
    
    @RequestMapping(value = "form/json")
    public ModelAndView formJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        return new ModelAndView("singleJson", "data",  ActorJsonForm.toJson(new Actor()));
    }
    
    @RequestMapping(value = "{id}/form")
    public ModelAndView form(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("id", id);
        model.addObject("table", "Actores");
        return model;
    }
    
    @RequestMapping(value = "new/form")
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("table", "Actores");
        model.addObject("form", ActorJsonForm.toJson(new Actor()));
        return model;
    }
    
    @RequestMapping(value = "{type}/modalList")
    public ModelAndView modalList(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("list");
        model.addObject("table", "actores");
        model.addObject("type", type);
        return model;
    }
    
    @RequestMapping(value = "{id}/view")
    public ModelAndView view(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "view.jsp");
        model.addObject("id", id);
        model.addObject("table", "Actores");
        return model;
    }

    @RequestMapping({"{form}/save"})
    public void save(@PathVariable String form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

        Actor actor = ActorJsonData.fromJson(form);
        
        if (actor.getId() == null) {
            dao.create(actor);
        } else {
            dao.update(actor);
        }
    }

    @RequestMapping({"{id}/delete"})
    public void delete(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

            Actor actor = new Actor();
            actor.setId(id);
            dao.delete(actor);
    }
}
