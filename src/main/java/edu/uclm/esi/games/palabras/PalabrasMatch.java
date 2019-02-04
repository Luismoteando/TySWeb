package edu.uclm.esi.games.palabras;

import java.util.Random;

import edu.uclm.esi.games.Match;
import edu.uclm.esi.games.Player;
import edu.uclm.esi.games.Result;
import edu.uclm.esi.games.palabras.PalabrasBoard;
import edu.uclm.esi.mongolabels.dao.MongoBroker;

public class PalabrasMatch extends Match{

	public PalabrasMatch() {
		super();
		this.board = new PalabrasBoard(this);
	}

	@Override
	protected void save() throws Exception {
		// TODO Auto-generated method stub
		Result result=new Result(this.getPlayers().get(0).getUserName(), this.getPlayers().get(1).getUserName(), this.winner.getUserName());
		MongoBroker.get().insert(result);
	}

	@Override
	protected boolean tieneElTurno(Player player) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void calculateFirstPlayer() {
		// TODO Auto-generated method stub
		boolean dado = new Random().nextBoolean();
		this.currentPlayer = dado ? 0 : 1;
	}
}
