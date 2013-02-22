/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.json;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.Director;
import net.ausiasmarch.pojo.Genero;
import net.ausiasmarch.pojo.Pelicula;
import net.ausiasmarch.utilities.Format;

/**
 * Custom Serialize/Unserialize Pelicula to Json
 *
 * @author María Galbis
 */
public class PeliculaJsonData implements JsonSerializer<Pelicula>, JsonDeserializer<Pelicula> {

    public static String toJson(Pelicula p) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Pelicula.class, new PeliculaJsonData()).create();
        return gson.toJson(p);
    }

    public static String toJson(List<Pelicula> p) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Pelicula.class, new PeliculaJsonData()).create();
        return gson.toJson(p);
    }

    public static Pelicula fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Pelicula.class, new PeliculaJsonData()).create();
        return gson.fromJson(str, Pelicula.class);
    }

    @Override
    public JsonElement serialize(Pelicula src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        if (src != null) {
            jsonObject.addProperty("Id", src.getId());
            jsonObject.addProperty("Título", src.getTitulo());
            jsonObject.addProperty("VO", (src.getVo() == null ? "" : src.getVo()));
            jsonObject.addProperty("Descripción", (src.getDescripcion() == null ? "" : src.getDescripcion()));
            jsonObject.addProperty("Calificación", src.getCalificacion());
            jsonObject.addProperty("Duración", src.getDuracion());
            jsonObject.addProperty("Fecha", Format.fromMySql(src.getFecha()));

            JsonObject genero = new JsonObject();
            genero.addProperty("id", src.getGenero().getId());
            genero.addProperty("nombre", src.getGenero().getNombre());

            jsonObject.add("Género", genero);

            JsonObject director = new JsonObject();
            director.addProperty("id", src.getDirector().getId());
            director.addProperty("nombre", src.getDirector().getNombre());

            jsonObject.add("Director", director);

            JsonArray intArr = new JsonArray();

            for (Actor a : (Set<Actor>) src.getActores()) {
                JsonObject aux = new JsonObject();
                aux.addProperty("id", a.getId());
                aux.addProperty("nombre", a.getNombre());

                intArr.add(aux);
            }

            jsonObject.add("Actores", intArr);
        }
        return jsonObject;
    }

    @Override
    public Pelicula deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Pelicula pelicula = new Pelicula();
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
                        pelicula.setId(Integer.parseInt(o.toString()));
                    }
                    break;
                case "titulo":
                    o = (Object) e.getValue().toString().replace("\"", "");
                    
                    if (o.toString() != null && !o.toString().equals("")) {
                        pelicula.setTitulo((String) o);
                    }
                    break;
                case "vo":
                    o = (Object) e.getValue().toString().replace("\"", "");
                    
                    if (o.toString() != null && !o.toString().equals("")) {
                        pelicula.setVo((String) o);
                    }
                    break;
                case "descripcion":
                    o = (Object) e.getValue().toString().replace("\"", "");
                    
                    if (o.toString() != null && !o.toString().equals("")) {
                        pelicula.setDescripcion((String) o);
                    }
                    break;
                case "calificacion":
                    o = (Object) e.getValue().toString().replace("\"", "");
                    
                    if (o.toString() != null && !o.toString().equals("")) {
                        pelicula.setCalificacion(Integer.parseInt(o.toString()));
                    }
                    break;
                case "duracion":
                    o = (Object) e.getValue().toString().replace("\"", "");
                    
                    if (o.toString() != null && !o.toString().equals("")) {
                        pelicula.setDuracion(Integer.parseInt(o.toString()));
                    }
                    break;
                case "fecha":
                    o = (Object) e.getValue().toString().replace("\"", "");
                    
                    if (o.toString() != null && !o.toString().equals("")) {
                        Calendar cal = new GregorianCalendar();
                        cal.set(Format.year(o.toString()), Format.month(o.toString()), Format.day(o.toString()));
                        pelicula.setFecha(cal.getTime());
                    }
                    break;
                   
                case "genero":
                    if (e.getValue() != null && !e.getValue().toString().equals("")) {
                        
                        Genero g = new Genero();

                        JsonObject aux = (JsonObject) e.getValue();
                        Set set2 = aux.entrySet();
                        
                        Iterator i = set2.iterator();
                        while (i.hasNext()) {
                            Map.Entry e2 = (Map.Entry) i.next();
                            
                            switch (e2.getKey().toString()) {
                                case "id":
                                    o = (Object) e2.getValue().toString().replace("\"", "");
                                    
                                    g.setId(Integer.parseInt(o.toString()));
                                    break;
                                case "nombre":
                                    o = (Object) e2.getValue().toString().replace("\"", "");
                                    
                                    g.setNombre((String) o);
                                    break;
                            }
                            
                            pelicula.setGenero(g);
                        }
                    }
                    break;
                case "director":
                    if (e.getValue() != null && !e.getValue().toString().equals("")) {
                        Director d = new Director();

                        JsonObject aux = (JsonObject) e.getValue();
                        Set set2 = aux.entrySet();
                        
                        Iterator i = set2.iterator();
                        while (i.hasNext()) {
                            Map.Entry e2 = (Map.Entry) i.next();
                            
                            switch (e2.getKey().toString()) {
                                case "id":
                                    o = (Object) e2.getValue().toString().replace("\"", "");
                                    
                                    d.setId(Integer.parseInt(o.toString()));
                                    break;
                                case "nombre":
                                    o = (Object) e2.getValue().toString().replace("\"", "");
                                    
                                    d.setNombre((String) o);
                                    break;
                            }

                            pelicula.setDirector(d);
                        }
                    }
                    break;

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
                                
                                switch (e2.getKey().toString()) {
                                    case "id":
                                        o = (Object) e2.getValue().toString().replace("\"", "");
                                        
                                        actor.setId(Integer.parseInt(o.toString()));
                                        break;
                                    case "nombre":
                                        o = (Object) e2.getValue().toString().replace("\"", "");
                                        
                                        actor.setNombre((String) o);
                                        break;
                                }

                                
                            }
                            se.add(actor);
                        }

                        pelicula.setActores(se);
                     
                    }
                    break;
            
            }


        }
        
        

        return pelicula;
    }
}
