package edu.uclm.esi.dao;

import java.util.Random;

import edu.uclm.esi.web.EmailSender;

public class DAOUsuario {
	public static void recuperarPWD(String email) throws Exception {

		long codigo = new Random().nextLong();
		EmailSender servicioRecuperacion = new EmailSender();
		servicioRecuperacion.enviar(email, codigo);
		//insertRecuperacion(email, codigo);

	}
}
