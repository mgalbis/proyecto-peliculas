/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.json;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.Pelicula;

/**
 * Custom Serialize Actor to Json
 * @author María Galbis
 */
public class ActorJsonData implements JsonSerializer<Actor>, JsonDeserializer<Actor>{

    public static String toJson(Actor a){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Actor.class, new ActorJsonData()).create();
        return gson.toJson(a);
    }
    
    public static String toJson(List<Actor> a){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Actor.class, new ActorJsonData()).create();
        return gson.toJson(a);
    }
    
    public static Actor fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Actor.class, new ActorJsonData()).create();
        return gson.fromJson(str, Actor.class);
    }
     
    @Override
    public JsonElement serialize(Actor src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Id", src.getId());
        jsonObject.addProperty("Nombre", src.getNombre());
        
        JsonArray intArr = new JsonArray();
        
        for(Pelicula p : (Set<Pelicula>) src.getPeliculas()){
            JsonObject aux = new JsonObject();
            aux.addProperty("id", p.getId());
            aux.addProperty("titulo", p.getTitulo());
            
            intArr.add(aux);
        }

        jsonObject.add("peliculas", intArr);
        
        
        return jsonObject;     
    }

    @Override
    public Actor deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Actor actor = new Actor();
        JsonObject jsonObj = json.getAsJsonObject();
        Set set = jsonObj.entrySet();
        
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            
            Object o;
            switch (e.getKey().toString()) {
                case "id":
                    o = (Object) e.getValue().toString().replace("\"", "");
                    
                    if (o.toString() != null && !o.toString().equals("")) {
                        actor.setId(Integer.parseInt(o.toString()));
                    }
                    break;
                case "nombre":
                    o = (Object) e.getValue().toString().replace("\"", "");
                    
                    if (o.toString() != null && !o.toString().equals("")) {
                        actor.setNombre((String) o);
                    }
                    break;
                
                case "peliculas":
                    if (e.getValue() != null && !e.getValue().toString().equals("")) {
                        JsonArray jarr = (JsonArray) e.getValue();
                        
                        Set se = new HashSet();
                        for (JsonElement je : jarr) {
                            Pelicula pelicula = new Pelicula();
                            
                            JsonObject aux = je.getAsJsonObject();
                            Set set2 = aux.entrySet();

                            Iterator i = set2.iterator();
                            while (i.hasNext()) {
                                Map.Entry e2 = (Map.Entry) i.next();
                                
                                switch (e2.getKey().toString()) {
                                    case "id":
                                        o = (Object) e2.getValue().toString().replace("\"", "");
                                        
                                        pelicula.setId(Integer.parseInt(o.toString()));
                                        break;
                                    case "titulo":
                                        o = (Object) e2.getValue().toString().replace("\"", "");
                                        
                                        pelicula.setTitulo((String) o);
                                        break;
                                }

                                
                            }
                            se.add(actor);
                        }

                        actor.setPeliculas(se);
                     
                    }
                    break;
          
            }


        }
        
        

        return actor;
    
    }
    
}
