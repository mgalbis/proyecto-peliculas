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
    
    @RequestMapping({"index.html"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {      
        ModelAndView mod = new ModelAndView("index", "contenido", "table.jsp");
        mod.addObject("table", "Actores");
        return mod;
    }
    
    @RequestMapping({"list.json"})
    public ModelAndView generos(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        List<Actor> actores = dao.readAll();
        String data = ActorJsonData.toJson(actores);
        
        return new ModelAndView("listJson", "data", data);
    }
    
    @RequestMapping(value = "{id}/single.json")
    public ModelAndView genero(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Actor actor = new Actor();
        actor.setId(id);
        actor = dao.read(actor);
           
        String data = ActorJsonData.toJson(actor);
       
        return new ModelAndView("singleJson", "data", data);
    }
    
    @RequestMapping(value = "form.json")
    public ModelAndView formJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        return new ModelAndView("singleJson", "data",  ActorJsonForm.toJson(new Actor()));
    }
    
    @RequestMapping(value = "{id}/form.html")
    public ModelAndView form(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("id", id);
        model.addObject("table", "Actores");
        return model;
    }
    
    @RequestMapping(value = "form.html")
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("table", "Actores");
        model.addObject("form", ActorJsonForm.toJson(new Actor()));
        return model;
    }
    
    @RequestMapping(value = "{type}/modalList.html")
    public ModelAndView modalList(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("list");
        model.addObject("table", "actores");
        model.addObject("type", type);
        return model;
    }
    
    @RequestMapping(value = "{id}/view.html")
    public ModelAndView view(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "view.jsp");
        model.addObject("id", id);
        model.addObject("table", "Actores");
        return model;
    }

    @RequestMapping({"save.html"})
    public void save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

        Actor actor = ActorJsonData.fromJson(request.getParameter("form"));
        
        if (actor.getId() == null) {
            dao.create(actor);
        } else {
            dao.update(actor);
        }
    }

    @RequestMapping({"{id}/delete.html"})
    public void delete(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

            Actor actor = new Actor();
            actor.setId(id);
            dao.delete(actor);
    }
}
