import java.util.ArrayList;
import java.util.Random;

public class Plantain {

	private final String BOT_VERSION = "0.1.0 Eureka";
	public final Config CONFIG;

	ArrayList<Command> commands;
	Random random;
	Game game;

	public Plantain(Random rng, Game g, Config c) {
		random = rng;
		game = g;
		CONFIF = c;
		Log.log("Starting Plantain...\nBot Version: " + BOT_VERSION + "\n");
	}

	public void initialize() {
		//Do nothing for now.
		Log.log("Nothing to do in initialize() :)");

		Log.log("Plantain successfully started.");
	}

	public void runTurn(Player me, GameMap map) {
		commands = new ArrayList<>();
		//Run turn logic
		templateTurn(me, map);
		game.endTurn(commands);
	}

	public void eurekaTurn() {
		//Versions 0.1.?

		//Add_code_here
	}

	private void templateTurn(Player p, GameMap m) {
		for (Ship ship : p.ships.values()) {//Ship values are wrong...?
            if (m.at(ship).halite < Constants.MAX_HALITE / 10 || ship.isFull()) {
                Direction randomDirection = Direction.ALL_CARDINALS.get(random.nextInt(4));
                commands.add(ship.move(randomDirection));
            } else {
                commands.add(ship.stayStill());
            }
        }

        if (game.turnNumber <= 200 && p.halite >= Constants.SHIP_COST && !m.at(p.shipyard).isOccupied())
        {
            commands.add(p.shipyard.spawn());
        }
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