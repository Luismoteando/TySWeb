package edu.uclm.esi.web;

import edu.uclm.esi.dao.DAOUsuario;
import edu.uclm.esi.games.Player;

public class UsuarioRegistrado extends Player{
	
	public UsuarioRegistrado() {
		super();
	}
	
	public static void recuperarPWD (String email) throws Exception {
		DAOUsuario.recuperarPWD(email);
	}

}
