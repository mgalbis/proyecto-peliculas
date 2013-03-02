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
import net.ausiasmarch.dao.DirectorDao;
import net.ausiasmarch.json.ActorJsonForm;
import net.ausiasmarch.json.DirectorJsonData;
import net.ausiasmarch.json.DirectorJsonForm;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.Director;
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
@RequestMapping({"/directores"})
public class DirectorController {
    
    @Autowired
    DirectorDao dao;
 
    
    @RequestMapping({""})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {      
        ModelAndView mod = new ModelAndView("index", "contenido", "table.jsp");
        mod.addObject("table", "Directores");
        return mod;
    }
    
    @RequestMapping({"all/json"})
    public ModelAndView directores(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView mod = new ModelAndView("listJson");
        
        List<Director> directores = dao.readAll();
        String data = DirectorJsonData.toJson(directores);
        mod.addObject("data", data);
        
        return mod;
    }
    
    @RequestMapping({"{limit}/{page}/json"})
    public ModelAndView getPage(@PathVariable Integer limit, @PathVariable Integer page, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        List<Director> directores = dao.getPage(limit, page);
        String data = DirectorJsonData.toJson(directores);

        return new ModelAndView("listJson", "data", data);
    }
    
    @RequestMapping({"pages/{limit}"})
    public ModelAndView getPages(@PathVariable Integer limit, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        int pages = dao.getPages(limit);
        
        String data = "{\"pages\":"+pages+"}";

        return new ModelAndView("singleJson", "data", data);
    }
    
    @RequestMapping(value = "{id}/json")
    public ModelAndView director(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Director director = dao.read(id);
           
        String data = DirectorJsonData.toJson(director);
        
        return new ModelAndView("singleJson", "data", data);
    }
    
    @RequestMapping(value = "form/json")
    public ModelAndView formJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        return new ModelAndView("singleJson", "data",  DirectorJsonForm.toJson(new Director()));
    }
    
    @RequestMapping(value = "{id}/form")
    public ModelAndView form(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("id", id);
        model.addObject("table", "Directores");
        return model;
    }
    
    @RequestMapping(value = "new/form")
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("table", "Directores");
        return model;
    }
    
    @RequestMapping(value = "{type}/modalList")
    public ModelAndView modalList(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("list");
        model.addObject("table", "directores");
        model.addObject("type", type);
        return model;
    }
    
    @RequestMapping(value = "{id}/view")
    public ModelAndView view(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "view.jsp");
        model.addObject("id", id);
        model.addObject("table", "Directores");
        return model;
    }
    
    @RequestMapping({"{form}/save"})
    public void save(@PathVariable String form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

        Director director = DirectorJsonData.fromJson(form);
        
        if (director.getId() == null) {
            dao.create(director);
        } else {
            dao.update(director);
        }
    }
    
    @RequestMapping({"{id}/delete"})
    public void delete(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

            Director director = new Director();
            director.setId(id);
            dao.delete(director);
    }
}
