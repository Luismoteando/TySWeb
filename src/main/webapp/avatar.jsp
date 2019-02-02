<%@page import="edu.uclm.esi.web.*"%>
<%@page import="org.bson.BsonDocument" %>
<%@page import="edu.uclm.esi.mongolabels.dao.*"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String p = request.getParameter("p");
	JSONObject jso = new JSONObject(p);
	
	try {
		String userName = jso.optString("userName");
		BsonDocument avatar = Manager.get().getAvatar(userName);
		String foto = avatar.get("path").asString().getValue();
		
		JSONObject respuesta = new JSONObject();
		respuesta.put("avatar", foto);
		respuesta.put("result", "OK");
		
		out.println(respuesta.toString());
	} catch (Exception e) {
		jso.put("result", "ERROR");
		jso.put("mensaje", e.getMessage());
	}
%>