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
import net.ausiasmarch.dao.PeliculaDao;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.Pelicula;

/**
 *
 * @author María Galbis
 */
public class ActorJsonForm implements JsonSerializer<Actor> {
    private PeliculaDao peliculaDao = new PeliculaDao();
    
    public static String toJson(Actor p) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Actor.class, new ActorJsonForm()).create();
        return gson.toJson(p);
    }

    @Override
    public JsonElement serialize(Actor src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
      
            jsonObject.addProperty("Id", "hidden");
            jsonObject.addProperty("Nombre", "text");
            
            JsonArray peliculas = new JsonArray();

            for (Pelicula p : (List<Pelicula>) peliculaDao.readAll()) {
                JsonObject aux = new JsonObject();
                aux.addProperty("id", p.getId());
                aux.addProperty("titulo", p.getTitulo());

                peliculas.add(aux);
            }
            
            JsonObject aux2 = new JsonObject();
            aux2.add("multiple", peliculas);
            jsonObject.add("Películas", aux2);
            
           
        return jsonObject;
    }
}
