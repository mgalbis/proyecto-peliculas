<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%
    out.print("{\"list\":" + request.getAttribute("data") + "}");
%>
