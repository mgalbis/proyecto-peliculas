<%@page import="net.ausiasmarch.utilities.ActorJsonAdapter"%>
<%@page import="net.ausiasmarch.pojo.Actor"%>
<%@page import="java.util.List"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@page import="com.google.gson.Gson"%>
<%
    List<Actor> actores = (List<Actor>) request.getAttribute("actores");
    String data = ActorJsonAdapter.toJson(actores);
    out.print("{\"list\":" + data + "}");
%>
