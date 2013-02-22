/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.ausiasmarch.pojo.Director;
import net.ausiasmarch.pojo.Pelicula;

/**
 *
 * @author María Galbis
 */
public class DirectorJsonData implements JsonSerializer<Director>, JsonDeserializer<Director> {

    public static String toJson(Director d){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Director.class, new DirectorJsonData()).create();
        return gson.toJson(d);
    }
    
    public static String toJson(List<Director> d){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Director.class, new DirectorJsonData()).create();
        return gson.toJson(d);
    }
    
    public static Director fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Director.class, new DirectorJsonData()).create();
        return gson.fromJson(str, Director.class);
    }

    @Override
    public JsonElement serialize(Director src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Id", src.getId());
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

    @Override
    public Director deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Director director = new Director();
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
                        director.setId(Integer.parseInt(o.toString()));
                    }
                    break;
                case "nombre":
                    o = (Object) e.getValue().toString().replace("\"", "");
                    
                    if (o.toString() != null && !o.toString().equals("")) {
                        director.setNombre((String) o);
                    }
                    break;
                /*
                case "actores":
                    if (e.getValue() != null && !e.getValue().toString().equals("")) {
                        JsonArray jarr = (JsonArray) e.getValue();
                        
                        Set se = new HashSet();
                        for (JsonElement je : jarr) {
                            Actor actor = new Actor();
                            
                            JsonObject aux = je.getAsJsonObject();
                            Set set2 = aux.entrySet();

                            Iterator i = set2.iterator();
                            while (i.hasNext()) {
                                Map.Entry e2 = (Map.Entry) i.next();
                                System.out.println(e2.getKey());
                                switch (e2.getKey().toString()) {
                                    case "id":
                                        o = (Object) e2.getValue().toString().replace("\"", "");
                                        System.out.println(o);
                                        actor.setId(Integer.parseInt(o.toString()));
                                        break;
                                    case "nombre":
                                        o = (Object) e2.getValue().toString().replace("\"", "");
                                        System.out.println(o);
                                        actor.setNombre((String) o);
                                        break;
                                }

                                
                            }
                            se.add(actor);
                        }

                        pelicula.setActores(se);
                     
                    }
                    break;
            */
            }


        }
        
        

        return director;
    }
    
}
