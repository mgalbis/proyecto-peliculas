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
import net.ausiasmarch.dao.PeliculaDao;
import net.ausiasmarch.json.PeliculaJsonData;
import net.ausiasmarch.json.PeliculaJsonForm;
import net.ausiasmarch.pojo.Pelicula;
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
@RequestMapping({"/peliculas"})
public class PeliculaController {

    @Autowired
    PeliculaDao dao;

    @RequestMapping({""})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView mod = new ModelAndView("index", "contenido", "table.jsp");
        mod.addObject("table", "Películas");
        return mod;
    }

    @RequestMapping({"all/json"})
    public ModelAndView peliculas(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        List<Pelicula> peliculas = dao.readAll();
        String data = PeliculaJsonData.toJson(peliculas);

        return new ModelAndView("listJson", "data", data);
    }

    @RequestMapping(value = "{id}/json")
    public ModelAndView pelicula(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Pelicula pelicula = dao.read(id);
        String data = PeliculaJsonData.toJson(pelicula);

        return new ModelAndView("singleJson", "data", data);
    }
    
    @RequestMapping(value = "form/json")
    public ModelAndView formJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        return new ModelAndView("singleJson", "data",  PeliculaJsonForm.toJson(new Pelicula()));
    }

    @RequestMapping(value = "{id}/form")
    public ModelAndView form(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("id", id);
        model.addObject("table", "Películas");
        return model;
    }
    
    @RequestMapping(value = "new/form")
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("table", "Películas");
        return model;
    }
    
    @RequestMapping(value = "{type}/modalList")
    public ModelAndView modalList(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("list");
        model.addObject("table", "peliculas");
        model.addObject("type", type);
        return model;
    }

    @RequestMapping(value = "{id}/view")
    public ModelAndView view(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "view.jsp");
        model.addObject("id", id);
        model.addObject("table", "Películas");
        return model;
    }

    @RequestMapping({"{form}/save"})
    public void save(@PathVariable String form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

        Pelicula pelicula = PeliculaJsonData.fromJson(form);
        
        if (pelicula.getId() == null) {
            dao.create(pelicula);
        } else {
            dao.update(pelicula);
        }
    }

    @RequestMapping({"{id}/delete"})
    public void delete(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

            Pelicula pelicula = new Pelicula();
            pelicula.setId(id);
            dao.delete(pelicula);
    }
}
