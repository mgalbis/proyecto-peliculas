/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.ausiasmarch.pojo.HibernateUtil;
import net.ausiasmarch.pojo.Usuario;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author María Galbis
 */
@Controller
public class PeliculaController {
    
    @RequestMapping({"/index.html"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Usuario u = (Usuario) session.get(Usuario.class, 1);
        System.out.println(u.getId()+" "+u.getLogin()+" "+u.getPassword());
        return new ModelAndView("index");
    }
    
}
