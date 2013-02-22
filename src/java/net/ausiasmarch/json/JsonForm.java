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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.ausiasmarch.common.Convert;
import net.ausiasmarch.pojo.Actor;
import net.ausiasmarch.pojo.Director;
import net.ausiasmarch.pojo.GenericPojo;
import net.ausiasmarch.pojo.Genero;
import net.ausiasmarch.utilities.Collections;

/**
 *
 * @author María Galbis
 */
public class JsonForm implements JsonSerializer {
    
    public static String toJson(GenericPojo a){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Object.class, new JsonForm()).create();
        System.out.println(gson.toJson(a));
        return gson.toJson(a);
    }
     
    @Override
    public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject(); //creamos un objeto Json
        for (Field f : src.getClass().getDeclaredFields()) { //recorremos los campos del pojo
            

                
                String s = f.getName();
                System.out.println(Collections.toCollectionClass(f.getType()).getSimpleName());

                String dateType = Collections.toCollectionClass(f.getType()).getSimpleName().toLowerCase();
                
                dateType = Convert.isValidDate(dateType)? "date" : dateType;
                dateType = dateType.length() > 100 ? "long" : dateType;
                
            jsonObject.addProperty(f.getName(), dateType);
            
            JsonArray directores = new JsonArray();
/*
            for (Director d : (List<Director>) directorDao.readAll()) {
                JsonObject aux = new JsonObject();
                aux.addProperty("id", d.getId());
                aux.addProperty("nombre", d.getNombre());

                directores.add(aux);
            }
            
            JsonObject aux2 = new JsonObject();
            aux2.add("list", directores);
            jsonObject.add("director", aux2);
            
            
            JsonArray generos = new JsonArray();
            for (Genero g : (List<Genero>) generoDao.readAll()) {
                JsonObject aux = new JsonObject();
                aux.addProperty("id", g.getId());
                aux.addProperty("nombre", g.getNombre());

                generos.add(aux);
            }
            JsonObject aux3 = new JsonObject();
            aux3.add("list", generos);
            jsonObject.add("genero", aux3);

            JsonArray intArr = new JsonArray();

            for (Actor a : (List<Actor>) actorDao.readAll()) {
                JsonObject aux = new JsonObject();
                aux.addProperty("id", a.getId());
                aux.addProperty("nombre", a.getNombre());

                intArr.add(aux);
            }

            jsonObject.add("Actores", intArr);*/
        }
        return jsonObject;    
    }
    
}
