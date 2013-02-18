/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import net.ausiasmarch.dao.ActorDao;
import net.ausiasmarch.dao.DirectorDao;
import net.ausiasmarch.dao.GeneroDao;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.Director;
import net.ausiasmarch.pojo.Genero;
import net.ausiasmarch.pojo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author María Galbis
 */
public class PeliculaJsonForm implements JsonSerializer<Pelicula> {

    private GeneroDao generoDao = new GeneroDao();
    private DirectorDao directorDao = new DirectorDao();
    private ActorDao actorDao = new ActorDao();
    
    public static String toJson(Pelicula p) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Pelicula.class, new PeliculaJsonForm()).create();
        return gson.toJson(p);
    }

    @Override
    public JsonElement serialize(Pelicula src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
      
            jsonObject.addProperty("id", "");
            jsonObject.addProperty("titulo", "");
            jsonObject.addProperty("vo", "");
            jsonObject.addProperty("descripcion", "long");
            jsonObject.addProperty("calificacion", "");
            jsonObject.addProperty("duracion", "");
            jsonObject.addProperty("fecha", "date");
            
            JsonArray directores = new JsonArray();

            for (Director d : (List<Director>) directorDao.readAll()) {
                JsonObject aux = new JsonObject();
                aux.addProperty("id", d.getId());
                aux.addProperty("nombre", d.getNombre());

                directores.add(aux);
            }
            
            JsonObject aux2 = new JsonObject();
            aux2.add("list", directores);
            jsonObject.add("director", aux2);
            
            
            JsonArray generos = new JsonArray();
            for (Genero g : (List<Genero>) generoDao.readAll()) {
                JsonObject aux = new JsonObject();
                aux.addProperty("id", g.getId());
                aux.addProperty("nombre", g.getNombre());

                generos.add(aux);
            }
            JsonObject aux3 = new JsonObject();
            aux3.add("list", generos);
            jsonObject.add("genero", aux3);

            JsonArray intArr = new JsonArray();

            for (Actor a : (List<Actor>) actorDao.readAll()) {
                JsonObject aux = new JsonObject();
                aux.addProperty("id", a.getId());
                aux.addProperty("nombre", a.getNombre());

                intArr.add(aux);
            }

            jsonObject.add("Actores", intArr);
      
        
        return jsonObject;
    }
    
}
