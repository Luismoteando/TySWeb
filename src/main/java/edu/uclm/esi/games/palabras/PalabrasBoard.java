package edu.uclm.esi.games.palabras;

import edu.uclm.esi.games.Board;
import edu.uclm.esi.games.Match;
import edu.uclm.esi.games.Player;

public class PalabrasBoard extends Board{
	
	private int[] tiradas0, tiradas1;

	public PalabrasBoard(PalabrasMatch palabrasMatch) {
		super(palabrasMatch);
		this.tiradas0 = new int[] {-1};
		this.tiradas1 = new int[] {-1};
	}

	@Override
	public void move(Player player, int[] coordinates) throws Exception {
		int pos;
		if(this.match.getPlayers().get(0) == player) {
			pos = rellenar(tiradas0, coordinates[0]);
		} else {
			pos = rellenar(tiradas1, coordinates[0]);
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
		for(int i = 0; i < tiradas0.length; i++) {
			if(tiradas0[i] == -1 || tiradas1[i] == -1) {
				return null;
			}
		}
		return gana(tiradas0, tiradas1);
	}

	private Player gana(int[] tiradas0, int[] tiradas1) {
		int victorias0 = 0, victorias1 = 0;
		for(int i = 0; i < tiradas0.length; i++) {
			if(gana(tiradas0[i], tiradas1[i])) {
				victorias0++;
			} else {
				victorias1++;
			}
		}
		return victorias0 > victorias1 ? this.match.getPlayers().get(0) : this.match.getPlayers().get(1);
	}
	
	private boolean gana(int tiradas0, int tiradas1) {
		if(tiradas0 == 1) {
			return true;
		} else if(tiradas1 == 1){
			return false;
		}
		return false;
	}

	@Override
	public boolean end() {
		if(this.getWinner() != null) {
			return true;
		}
		for(int i = 0; i < tiradas0.length; i++) {
			if(tiradas0[i] == -1 || tiradas1[i] == -1) {
				return false;
			}
		}
		return true;
	}

}
