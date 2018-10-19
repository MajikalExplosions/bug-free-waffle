import java.util.ArrayList;
import java.util.Random;

public class Plantain {

	ArrayList<Command> commands;
	Random random;
	Game game;

	public Plantain(Random rng, Game g) {
		random = rng;
		game = g;
	}

	public void runTurn(Player me, GameMap map) {
		commands = new ArrayList<>();
		//Run turn logic
		game.endTurn(commands);
	}

	/*
	MoveCalculator allows the code to calculate moves without slowing down the 2s turn limit; it's not useful for now but it might be in the future.
	I'm considering making it update movements for the next turn if the old path a ship was supposed to cross changes.
	*/
	private class MoveCalculator extends Thread {

		public MoveCalculator() {

		}

		public void run() {

		}
	}
}