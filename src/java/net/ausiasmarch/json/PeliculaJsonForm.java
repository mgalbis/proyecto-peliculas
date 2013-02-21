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
import net.ausiasmarch.dao.ActorDao;
import net.ausiasmarch.dao.DirectorDao;
import net.ausiasmarch.dao.GeneroDao;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.Director;
import net.ausiasmarch.pojo.Genero;
import net.ausiasmarch.pojo.Pelicula;

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
      
            jsonObject.addProperty("Id", "hidden");
            jsonObject.addProperty("Título", "text");
            jsonObject.addProperty("VO", "text");
            jsonObject.addProperty("Descripción", "long");
            jsonObject.addProperty("Calificación", "number");
            jsonObject.addProperty("Duración", "number");
            jsonObject.addProperty("Fecha", "date");
            
            JsonArray directores = new JsonArray();

//            for (Director d : (List<Director>) directorDao.readAll()) {
//                JsonObject aux = new JsonObject();
//                aux.addProperty("id", d.getId());
//                aux.addProperty("nombre", d.getNombre());
//
//                directores.add(aux);
//            }
            
            JsonObject aux2 = new JsonObject();
            aux2.add("simple", directores);
            jsonObject.add("Director", aux2);
            
            
            JsonArray generos = new JsonArray();
            for (Genero g : (List<Genero>) generoDao.readAll()) {
                JsonObject aux = new JsonObject();
                aux.addProperty("id", g.getId());
                aux.addProperty("nombre", g.getNombre());

                generos.add(aux);
            }
            
            jsonObject.add("Género", generos);

            JsonArray actores = new JsonArray();

//            for (Actor a : (List<Actor>) actorDao.readAll()) {
//                JsonObject aux = new JsonObject();
//                aux.addProperty("id", a.getId());
//                aux.addProperty("nombre", a.getNombre());
//
//                actores.add(aux);
//            }

            JsonObject aux3 = new JsonObject();
            aux3.add("multiple", actores);
            jsonObject.add("Actores", aux3);
      
        
        return jsonObject;
    }
    
}
