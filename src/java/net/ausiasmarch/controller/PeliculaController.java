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

    /**
     * Página de inicio de las películas
     */
    @RequestMapping({""})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView mod = new ModelAndView("index", "contenido", "table.jsp");
        mod.addObject("table", "Películas");
        return mod;
    }

    /**
     * Devuelve un json con todas las películas
     */
    @RequestMapping({"all/json"})
    public ModelAndView peliculas(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        List<Pelicula> peliculas = dao.readAll();
        String data = PeliculaJsonData.toJson(peliculas);

        return new ModelAndView("listJson", "data", data);
    }

    @RequestMapping({"{limit}/{page}/{search}/json"})
    public ModelAndView getPage(@PathVariable Integer limit, @PathVariable Integer page, @PathVariable String search, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        search = search.equals("null") ? null : search;
        
        List<Pelicula> peliculas = dao.getPage(limit, page, search);
        String data = PeliculaJsonData.toJson(peliculas);

        return new ModelAndView("listJson", "data", data);
    }
    
    @RequestMapping({"pages/{limit}/{search}"})
    public ModelAndView getPages(@PathVariable Integer limit, @PathVariable String search, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        search = search.equals("null") ? null : search;
        
        int pages = dao.getPages(limit, search);
        
        String data = "{\"pages\":"+pages+"}";

        return new ModelAndView("singleJson", "data", data);
    }
    
    /**
     * Devuelve un json de la película indicada
     * @param id Id de la película
     */
    @RequestMapping(value = "{id}/json")
    public ModelAndView pelicula(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Pelicula pelicula = dao.read(id);
        String data = PeliculaJsonData.toJson(pelicula);

        return new ModelAndView("singleJson", "data", data);
    }
    
    /**
     * Devuelve un json con los campos del formulario y el tipo de campo
     */
    @RequestMapping(value = "form/json")
    public ModelAndView formJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        return new ModelAndView("singleJson", "data",  PeliculaJsonForm.toJson(new Pelicula()));
    }

    /**
     * Crea un formulario para editar la película indicada
     * @param id Id de la película
     */
    @RequestMapping(value = "{id}/form")
    public ModelAndView form(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("id", id);
        model.addObject("table", "Películas");
        return model;
    }
    
    /**
     * Crea un formulario para un nuevo registro
     */
    @RequestMapping(value = "new/form")
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("table", "Películas");
        return model;
    }
    
    /**
     * Carga un modal con la lista de películas
     * @param type Tipo de lista (simple/multiple)
     */
    @RequestMapping(value = "{type}/modalList")
    public ModelAndView modalList(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("list");
        model.addObject("table", "peliculas");
        model.addObject("type", type);
        return model;
    }

    /**
     * Crea una vista para la película indicada
     * @param id Id de la película
     */
    @RequestMapping(value = "{id}/view")
    public ModelAndView view(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "view.jsp");
        model.addObject("id", id);
        model.addObject("table", "Películas");
        return model;
    }

    /**
     * Crea o edita un registro
     * @param form Datos de la película
     */
    @RequestMapping({"{form}/save"})
    public void save(@PathVariable String form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

        Pelicula pelicula = PeliculaJsonData.fromJson(form);
        
        if (pelicula.getId() == null) {
            dao.create(pelicula);
        } else {
            dao.update(pelicula);
        }
    }

    /**
     * Elimina la película indicada
     * @param id Id de la película
     */
    @RequestMapping({"{id}/delete"})
    public void delete(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

            Pelicula pelicula = new Pelicula();
            pelicula.setId(id);
            dao.delete(pelicula);
    }
}
