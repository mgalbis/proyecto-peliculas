/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ausiasmarch.dao.DirectorDao;
import net.ausiasmarch.json.DirectorJsonData;
import net.ausiasmarch.pojo.Director;
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
 
    
    @RequestMapping({"index.html"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {      
        ModelAndView mod = new ModelAndView("index", "contenido", "list.jsp");
        mod.addObject("table", "Directores");
        return mod;
    }
    
    @RequestMapping({"list.json"})
    public ModelAndView directores(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView mod = new ModelAndView("listJson");
        
        List<Director> directores = dao.readAll();
        String data = DirectorJsonData.toJson(directores);
        mod.addObject("data", data);
        
        return mod;
    }
    
    @RequestMapping(value = "{id}/single.json")
    public ModelAndView director(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Director director = new Director();
        director.setId(id);
        director = dao.read(director);
           
        String data = DirectorJsonData.toJson(director);
        
        return new ModelAndView("singleJson", "data", data);
    }
}
