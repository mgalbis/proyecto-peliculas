<%@page import="net.ausiasmarch.utilities.PeliculaJsonAdapter"%>
<%@page import="net.ausiasmarch.pojo.Pelicula"%>
<%@page import="java.util.List"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%
    List<Pelicula> pelicula = (List<Pelicula>) request.getAttribute("peliculas");
    String data = PeliculaJsonAdapter.toJson(pelicula);
    out.print("{\"list\":" + data + "}");
%>
