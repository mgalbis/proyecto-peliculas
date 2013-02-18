/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.json;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.Pelicula;
import net.ausiasmarch.utilities.Format;

/**
 * Custom Serialize/Unserialize Pelicula to Json
 *
 * @author María Galbis
 */
public class PeliculaJsonAdapter implements JsonSerializer<Pelicula>, JsonDeserializer<Pelicula> {

    public static String toJson(Pelicula p) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Pelicula.class, new PeliculaJsonAdapter()).create();
        return gson.toJson(p);
    }

    public static String toJson(List<Pelicula> p) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Pelicula.class, new PeliculaJsonAdapter()).create();
        return gson.toJson(p);
    }

    @Override
    public JsonElement serialize(Pelicula src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        if (src != null) {
            jsonObject.addProperty("#", src.getId());
            jsonObject.addProperty("Título", src.getTitulo());
            jsonObject.addProperty("VO", (src.getVo()==null?"":src.getVo()));
            jsonObject.addProperty("Descripción", (src.getDescripcion()==null?"":src.getDescripcion()));
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

            for (Actor a : (Set<Actor>) src.getActors()) {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
