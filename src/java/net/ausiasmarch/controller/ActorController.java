/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ausiasmarch.dao.ActorDao;
import net.ausiasmarch.json.ActorJsonData;
import net.ausiasmarch.pojo.Actor;
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
        ModelAndView mod = new ModelAndView("index", "contenido", "list.jsp");
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
}
