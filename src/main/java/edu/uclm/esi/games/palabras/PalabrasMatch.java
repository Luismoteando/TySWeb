package edu.uclm.esi.games.palabras;

import edu.uclm.esi.games.Match;
import edu.uclm.esi.games.Player;
import edu.uclm.esi.games.palabras.PalabrasBoard;

public class PalabrasMatch extends Match{

	public PalabrasMatch() {
		super();
		this.board = new PalabrasBoard(this);
	}

	@Override
	protected void save() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean tieneElTurno(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void calculateFirstPlayer() {
		// TODO Auto-generated method stub
		
	}
}
