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
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.utilities.ActorJsonAdapter;
import net.ausiasmarch.utilities.GeneroJsonAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        return new ModelAndView("index", "contenido", "actoresList.jsp");
    }
    
    @RequestMapping({"list.json"})
    public ModelAndView generos(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        List<Actor> actores = dao.readAll();
        String data = ActorJsonAdapter.toJson(actores);
        
        return new ModelAndView("listJson", "data", data);
    }
    
    @RequestMapping({"single.json"})
    public ModelAndView genero(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Actor actor = new Actor();
        String data = "";
        if(request.getParameter("id") != null){
            actor.setId(Integer.parseInt(request.getParameter("id")));
            actor = dao.read(actor);
           
            data = ActorJsonAdapter.toJson(actor);
        }
        
        return new ModelAndView("singleJson", "data", data);
    }
}
