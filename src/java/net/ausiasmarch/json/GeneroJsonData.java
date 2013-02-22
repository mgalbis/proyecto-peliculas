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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.ausiasmarch.pojo.Genero;
import net.ausiasmarch.pojo.Pelicula;

/**
 *
 * @author María Galbis
 */
public class GeneroJsonData implements JsonSerializer<Genero>, JsonDeserializer<Genero> {
    
    public static String toJson(Genero g){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Genero.class, new GeneroJsonData()).create();
        return gson.toJson(g);
    }
    
    public static String toJson(List<Genero> g){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Genero.class, new GeneroJsonData()).create();
        return gson.toJson(g);
    }
    
    public static Genero fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Genero.class, new GeneroJsonData()).create();
        return gson.fromJson(str, Genero.class);
    }

    @Override
    public JsonElement serialize(Genero src, Type typeOfSrc, JsonSerializationContext context) {
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
    public Genero deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Genero genero = new Genero();
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
                        genero.setId(Integer.parseInt(o.toString()));
                    }
                    break;
                case "nombre":
                    o = (Object) e.getValue().toString().replace("\"", "");
                    
                    if (o.toString() != null && !o.toString().equals("")) {
                        genero.setNombre((String) o);
                    }
                    break;
                /*
                case "peliculas":
                    if (e.getValue() != null && !e.getValue().toString().equals("")) {
                        JsonArray jarr = (JsonArray) e.getValue();
                        
                        Set se = new HashSet();
                        for (JsonElement je : jarr) {
                            Pelicula actor = new Pelicula();
                            
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
                                    case "titulo":
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
        
        

        return genero;
    }
    
}
