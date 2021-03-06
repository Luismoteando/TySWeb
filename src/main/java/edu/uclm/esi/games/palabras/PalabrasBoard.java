package edu.uclm.esi.games.palabras;

import edu.uclm.esi.games.Board;
import edu.uclm.esi.games.Match;
import edu.uclm.esi.games.Player;

public class PalabrasBoard extends Board{

	private int[] tiradas0, tiradas1;

	public PalabrasBoard(PalabrasMatch palabrasMatch) {
		super(palabrasMatch);
		this.tiradas0 = new int[45];
		this.tiradas1 = new int[45];
		inicializar();
	}

	private void inicializar() {
		for(int i = 0; i < tiradas0.length; i++) {
			tiradas0[i] = -1;
			tiradas1[i] = -1;
		}
	}

	@Override
	public void move(Player player, int[] coordinates) throws Exception {
		int pos;
		if(coordinates[0] == 1) {
			if(this.match.getPlayers().get(0) == player) {
				pos = rellenar(tiradas0, coordinates[0]);
			} else {
				pos = rellenar(tiradas1, coordinates[0]);
			}
		} else {
			if(this.match.getPlayers().get(0) == player) {
				pos = rellenar(tiradas0, coordinates[0]);
			} else {
				pos = rellenar(tiradas1, coordinates[0]);
			}
		}
	}

	private int rellenar(int[] tiradas, int valor) {
		for(int i = 0; i < tiradas.length; i++) {
			if(tiradas[i] == -1) {
				tiradas[i] = valor;
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public Player getWinner() {
		return gana(tiradas0, tiradas1);
	}

	private Player gana(int[] tiradas0, int[] tiradas1) {
		int victorias0 = 0, victorias1 = 0;
		int points0 = 0, points1 = 0;
		Player winner = null;
		
		victorias0 = hits(tiradas0);
		victorias1 = hits(tiradas1);
		points0 = victorias0 - attempts(tiradas0);
		points1 = victorias1 - attempts(tiradas1);
		
		if(victorias0 == 9 && victorias1 == 9) {
			if(points0 > points1) {
				winner = this.match.getPlayers().get(0);
			} else {
				winner = this.match.getPlayers().get(1);
			}
		}
		
		return winner;
	}
	
	private int hits(int[] tiradas) {
		int victorias = 0;
		
		for(int i = 0; i < tiradas.length; i++) {
			if(tiradas[i] == 1) {
				victorias++;
			}
		}
		
		return victorias;
	}
	
	private int attempts(int[] tiradas) {
		for(int i = 0; i < tiradas.length; i++) {
			if(tiradas[i] == -1) {
				return i;
			}
		}
		return tiradas.length;
	}

	public int[] getTiradas1() {
		return tiradas0;
	}

	public int[] getTiradas2() {
		return tiradas1;
	}

	@Override
	public boolean end() {
		if(this.getWinner() != null) {
			return true;
		}
		
		for(int i = 0; i < tiradas0.length; i++) {
			if(tiradas0[i] == -1 && tiradas1[i] == -1) {
				return false;
			}
		}
		
		return true;
	}

}
