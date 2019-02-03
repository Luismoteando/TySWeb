package edu.uclm.esi.games.palabras;

import edu.uclm.esi.games.Game;
import edu.uclm.esi.games.Match;

public class Palabras extends Game{

	public Palabras() {
		super(2);
	}

	@Override
	public String getName() {
		return "Destape de palabras";
	}

	@Override
	protected Match createMatch() {
		// TODO Auto-generated method stub
		return new PalabrasMatch();
	}

}
