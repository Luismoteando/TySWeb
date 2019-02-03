package edu.uclm.esi.games.palabras;

import edu.uclm.esi.games.Board;
import edu.uclm.esi.games.Match;
import edu.uclm.esi.games.Player;

public class PalabrasBoard extends Board{

	public PalabrasBoard(PalabrasMatch match) {
		super(match);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(Player player, int[] coordinates) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean end() {
		// TODO Auto-generated method stub
		return false;
	}

}
