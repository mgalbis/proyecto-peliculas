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
import net.ausiasmarch.pojo.Director;
import net.ausiasmarch.pojo.Pelicula;

/**
 *
 * @author María Galbis
 */
public class DirectorJsonAdapter implements JsonSerializer<Director> {

    public static String toJson(Director d){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Director.class, new DirectorJsonAdapter()).create();
        return gson.toJson(d);
    }
    
    public static String toJson(List<Director> d){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Director.class, new DirectorJsonAdapter()).create();
        return gson.toJson(d);
    }

    @Override
    public JsonElement serialize(Director src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("#", src.getId());
        jsonObject.addProperty("Nombre", src.getNombre());
        
        JsonArray intArr = new JsonArray();
        
        for(Pelicula p : (Set<Pelicula>) src.getPeliculas()){
            JsonObject aux = new JsonObject();
            aux.addProperty("id", p.getId());
            aux.addProperty("Titulo", p.getTitulo());
            
            intArr.add(aux);
        }

        jsonObject.add("peliculas", intArr);
        
        
        return jsonObject; 
    }
    
}
