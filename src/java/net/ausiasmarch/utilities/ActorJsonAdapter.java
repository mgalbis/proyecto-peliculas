/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.utilities;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.Pelicula;

/**
 * Custom Serialize Actor to Json
 * @author María Galbis
 */
public class ActorJsonAdapter implements JsonSerializer<Actor>, JsonDeserializer<Actor>{

    public static String toJson(Actor a){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Actor.class, new ActorJsonAdapter()).serializeNulls().create();
        return gson.toJson(a);
    }
    
    public static String toJson(List<Actor> a){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Actor.class, new ActorJsonAdapter()).serializeNulls().create();
        return gson.toJson(a);
    }
     
    @Override
    public JsonElement serialize(Actor src, Type typeOfSrc, JsonSerializationContext context) {
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

        jsonObject.add("Actores", intArr);
        
        
        return jsonObject;     
    }

    @Override
    public Actor deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
