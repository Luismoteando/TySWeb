package edu.uclm.esi.web;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	private final Properties properties = new Properties();
	private String smtpHost, startTTLS, port;
	private String remitente, serverUser, userAutentication, pwd;

	private class autentificadorSMTP extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(remitente, pwd);
		}
	}

	public void enviar(String email, long codigo) throws MessagingException {
		this.smtpHost = "smtp.gmail.com";
		this.startTTLS = "true";
		this.port = "465";
		this.remitente = "tysweb2019alr@gmail.com";
		this.serverUser = "tysweb2019alr@gmail.com";
		this.userAutentication = "true";
		this.pwd = "AmandaLuismiRebeca2019";
		properties.put("mail.smtp.host", this.smtpHost);
		properties.put("mail.smtp.starttls.enable", this.startTTLS);
		properties.put("mail.smtp.port", this.port);
		properties.put("mail.smtp.mail.sender", this.remitente);
		properties.put("mail.smtp.user", this.serverUser);
		properties.put("mail.smtp.auth", this.userAutentication);
		properties.put("mail.smtp.socketFactory.port", this.port);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");

		Authenticator auth = new autentificadorSMTP();
		Session session = Session.getInstance(properties, auth);

		MimeMessage msg = new MimeMessage(session);
		msg.setSubject("LaOca2018 - recuperación de contraseña");
		msg.setText(
				"Pulsa en el siguiente enlace para crear una nueva contraseña: http://localhost:8080/newPWD.html?code="
						+ codigo);
		msg.setFrom(new InternetAddress(this.remitente));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		Transport.send(msg);
		
	}

}
