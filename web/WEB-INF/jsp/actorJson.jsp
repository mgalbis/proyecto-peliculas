<%@page import="net.ausiasmarch.pojo.Actor"%>
<%@page import="net.ausiasmarch.utilities.PeliculaJsonAdapter"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="java.util.List"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%
    Actor actor = (Actor) request.getAttribute("actor");            
    //String data = new Gson().toJson(oCliente);
    
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.registerTypeAdapter(Actor.class, new PeliculaJsonAdapter()).create();
    
    out.print(gson.toJson(actor));
%>
