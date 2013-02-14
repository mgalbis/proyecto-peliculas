<%@page import="net.ausiasmarch.utilities.PeliculaJsonAdapter"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="java.util.List"%>
<%@page import="net.ausiasmarch.pojo.Pelicula"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%
    Pelicula pelicula = (Pelicula) request.getAttribute("pelicula");            
    String data = PeliculaJsonAdapter.toJson(pelicula);
    out.print(data);
%>
