package edu.uclm.esi.games;

import org.bson.BsonDocument;
import org.bson.BsonString;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.uclm.esi.mongolabels.dao.MongoBroker;
import edu.uclm.esi.mongolabels.labels.Bsonable;
import edu.uclm.esi.web.EmailSender;

public class Player {
	@Bsonable
	private String userName;
	@Bsonable
	private String email;
	@Bsonable
	private String pwd;
	@JsonIgnore
	private Match currentMatch;
	@Bsonable
	private String foto;

	@Bsonable
	private String idGoogle;
	@Bsonable
	private String tipo;

	public String getUserName() {
		return userName;
	}

	public String getFoto() {
		return foto;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setFoto(String path) {
		this.foto = path;
	}

	private void setTipo(String tipo) {
		this.tipo = tipo;
	}

	private void setIdGoogle(String idGoogle) {
		this.idGoogle = idGoogle;
	}

	public static Player identify(String userName, String pwd) throws Exception {
		BsonDocument criterion = new BsonDocument();
		criterion.append("userName", new BsonString(userName)).put("pwd", new BsonString(pwd));
		Player player = (Player) MongoBroker.get().loadOne(Player.class, criterion);
		criterion = new BsonDocument();
		criterion.append("userName", new BsonString(userName));
		BsonDocument foto = MongoBroker.get().loadOne("Avatar", criterion);
		player.setFoto(foto.getString("path").getValue());

		return player;
	}

	public static Player register(String email, String userName, String pwd, String avatar) throws Exception {
		Player player = new Player();
		player.setEmail(email);
		player.setUserName(userName);
		player.setPwd(pwd);
		try {
			MongoBroker.get().insert(player);
			MongoBroker.get().insertAvatar("Avatar", userName, avatar);
		} catch (Exception e) {
			return null;
		}
		return player;
	}

	public void setCurrentMatch(Match match) {
		this.currentMatch = match;
	}

	public Match getCurrentMatch() {
		return currentMatch;
	}

	public Match move(int[] coordinates) throws Exception {
		return this.currentMatch.move(this, coordinates);
	}

	public static Player solicitarToken(String userName) {
		Player player = null;
		try {
			BsonDocument criterion = new BsonDocument();
			criterion.append("userName", new BsonString(userName));
			player = (Player) MongoBroker.get().loadOne(Player.class, criterion);
			player.createToken();
		} catch (Exception e) {

		}
		return player;
	}

	private void createToken() throws Exception {
		Token token = new Token(this.userName);
		MongoBroker.get().insert(token);
		EmailSender email = new EmailSender();
		email.enviar(this.email, token.getValor());
	}

	public static Player identifyGoogle(String idGoogle, String nombre, String email) throws Exception {
		BsonDocument criterio = new BsonDocument();
		criterio.append("idGoogle", new BsonString(idGoogle));
		criterio.append("userName", new BsonString(nombre));
		criterio.append("email", new BsonString(email));
		criterio.append("tipo", new BsonString("Google"));
		Player player = (Player) MongoBroker.get().loadOne(Player.class, criterio);
		return player;
	}

	public static Player registerGoogle(String idGoogle, String nombre, String email) throws Exception {
		Player player = new Player();
		player.setEmail(email);
		player.setUserName(nombre);
		player.setIdGoogle(idGoogle);
		player.setTipo("Google");
		MongoBroker.get().insert(player);
		return player;
	}

	public byte[] loadFoto() {
		try {
			BsonDocument criterion = new BsonDocument();
			criterion.append("userName", new BsonString(this.userName));
			BsonDocument result = MongoBroker.get().loadBinary("Fotos", criterion);
			return result.getBinary("bytes").getData();
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean cambiarContraseña(String pwd1, String code) throws Exception {
		boolean resultado = false;
		BsonDocument criterion = new BsonDocument();
		criterion.append("valor", new BsonString(code));
		Player player = null;
		Token token = null;
		token = (Token) MongoBroker.get().loadOne(Token.class, criterion);
		if (token.getCaducidad() < System.currentTimeMillis()) {
			return resultado;
		}
		BsonDocument criterion2 = new BsonDocument();
		criterion2.append("userName", new BsonString(token.getUserName()));
		player = (Player) MongoBroker.get().loadOne(Player.class, criterion2);
		MongoBroker.get().delete("Player", criterion2);
		player.setPwd(pwd1);
		MongoBroker.get().insert(player);
		resultado = true;
		return resultado;
	}
}
