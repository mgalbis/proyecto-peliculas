/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.utilities;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.Set;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.Pelicula;

/**
 * Custom Serialize/Unserialize Pelicula
 * @author María Galbis
 */
public class PeliculaJsonAdapter implements JsonSerializer<Pelicula>, JsonDeserializer<Pelicula> {

    public static String toJson(Pelicula p){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Pelicula.class, new PeliculaJsonAdapter()).create();
        return gson.toJson(p);
    }
    
    
    
    @Override
    public JsonElement serialize(Pelicula src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("#", src.getId());
        jsonObject.addProperty("Nombre", src.getTitulo());
        jsonObject.addProperty("V.O.", src.getVo());
        jsonObject.addProperty("Descripción", src.getDescripcion());
        jsonObject.addProperty("Calificación", src.getCalificacion());
        jsonObject.addProperty("Duración", src.getDuracion());
        jsonObject.addProperty("Fecha", DateFormat.fromMySql(src.getFecha()));
        jsonObject.addProperty("Género", src.getGenero().getNombre());
        jsonObject.addProperty("Director", src.getDirector().getNombre());
        
        JsonArray intArr = new JsonArray();
        
        for(Actor a : (Set<Actor>) src.getActors()){
            JsonObject aux = new JsonObject();
            aux.addProperty("id", a.getId());
            aux.addProperty("nombre", a.getNombre());
            
            intArr.add(aux);
        }

        jsonObject.add("Actores", intArr);
       
        return jsonObject;  
    }

    @Override
    public Pelicula deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
