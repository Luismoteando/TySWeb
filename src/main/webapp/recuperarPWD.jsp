<%@page import="edu.uclm.esi.web.*"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>


<% 
	String p = request.getParameter("p");
	JSONObject jso = new JSONObject(p);
	JSONObject respuesta = new JSONObject();
	
	try {
		String email = jso.optString("email"); //optString returns the empty string ("") if the key you specify doesn't exist
		checkCredentials(email);
		
		Manager.get().recuperarPWD(email);
		respuesta.put("resultado", "OK");
	} 
	catch (Exception e) {
		respuesta.put("resultado", "ERROR");
		respuesta.put("msg", e.getMessage());
	}
%>

<%= respuesta.toString()%>

<%! //Declaración de método global, será accesible desde cualquier lugar
private void checkCredentials (String email) throws Exception {
	if (email.length() == 0) {
		throw new Exception ("Debes introducir un email para recuperar la contraseña.");
	}
}
%>
	