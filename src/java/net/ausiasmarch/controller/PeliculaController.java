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

    @RequestMapping({"index.html"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView mod = new ModelAndView("index", "contenido", "list.jsp");
        mod.addObject("table", "Películas");
        return mod;
    }

    @RequestMapping({"list.json"})
    public ModelAndView peliculas(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        List<Pelicula> peliculas = dao.readAll();
        String data = PeliculaJsonData.toJson(peliculas);

        return new ModelAndView("listJson", "data", data);
    }

    @RequestMapping(value = "{id}/single.json")
    public ModelAndView pelicula(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(id);
        pelicula = dao.read(pelicula);

        String data = PeliculaJsonData.toJson(pelicula);

        return new ModelAndView("singleJson", "data", data);
    }

    @RequestMapping(value = "{id}/form.html")
    public ModelAndView form(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("id", id);
        model.addObject("table", "Peliculas");
        model.addObject("form", PeliculaJsonForm.toJson(new Pelicula()));
        return model;
    }
    
    @RequestMapping(value = "form.html")
    public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "form.jsp");
        model.addObject("table", "Peliculas");
        model.addObject("form", PeliculaJsonForm.toJson(new Pelicula()));
        return model;
    }

    @RequestMapping(value = "{id}/view.html")
    public ModelAndView view(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView model = new ModelAndView("index", "contenido", "peliculaView.jsp");

        Pelicula pelicula = new Pelicula();
        pelicula.setId(Integer.parseInt(request.getParameter("id")));
        model.addObject("pelicula", dao.read(pelicula));


        return model;
    }

    @RequestMapping({"save.html"})
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

        Pelicula pelicula = new Gson().fromJson(request.getParameter("form"), Pelicula.class);

        if (pelicula.getId() == null) {
            dao.create(pelicula);
        } else {
            dao.update(pelicula);
        }

        return new ModelAndView("index", "contenido", "peliculasList.jsp");
    }

    @RequestMapping({"create.html"})
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {

        Pelicula pelicula = new Gson().fromJson(request.getParameter("form"), Pelicula.class);
        dao.create(pelicula);

        return new ModelAndView("index", "contenido", "peliculaList.jsp");
    }

    @RequestMapping({"delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, HibernateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {


        if (request.getParameter("id") != null) {
            Pelicula pelicula = new Pelicula();
            pelicula.setId(Integer.parseInt(request.getParameter("id")));
            dao.delete(pelicula);
        }


        return new ModelAndView("index", "contenido", "peliculasList.jsp");
    }
}
