/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.controller;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ausiasmarch.dao.DirectorDao;
import net.ausiasmarch.pojo.Director;
import net.ausiasmarch.utilities.PeliculaJsonAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 
    
    @RequestMapping({"index.html"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {      
        return new ModelAndView("index", "contenido", "directoresList.jsp");
    }
    
    @RequestMapping({"list.json"})
    public ModelAndView peliculas(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        
        List<Director> directores = dao.readAll();
        String data = new Gson().toJson(directores);
        
        return new ModelAndView("listJson", "data", data);
    }
    
    @RequestMapping({"single.json"})
    public ModelAndView pelicula(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Director director = new Director();;
        String data = "";
        if(request.getParameter("id") != null){
            director.setId(Integer.parseInt(request.getParameter("id")));
            director = dao.read(director);
           
            data = new Gson().toJson(director);
        }
        
        return new ModelAndView("singleJson", "data", data);
    }
}
