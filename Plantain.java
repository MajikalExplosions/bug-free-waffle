import java.util.ArrayList;
import java.util.Random;

public class Plantain {

	private final String BOT_VERSION = "0.1.0 Eureka";

	ArrayList<Command> commands;
	Random random;
	Game game;

	public Plantain(Random rng, Game g) {
		random = rng;
		game = g;
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
		Log.log("This player's ships: " + me.ships.values());
		templateTurn(me, map);
		game.endTurn(commands);
	}

	private void templateTurn(Player p, GameMap m) {
		Log.log("Running turn for player " + p.id);
		Log.log("This player's ships3: " + p.ships.values());
		for (Ship ship : p.ships.values()) {//Ship values are wrong...?
			Log.log("CalcShip: " + ship);
            if (m.at(ship).halite < Constants.MAX_HALITE / 10 || ship.isFull()) {
                Direction randomDirection = Direction.ALL_CARDINALS.get(random.nextInt(4));
                commands.add(ship.move(randomDirection));
                Log.log("Moved a ship.");
            } else {
                commands.add(ship.stayStill());
                Log.log("Ship staying still.");
            }
        }

        if (game.turnNumber <= 200 && p.halite >= Constants.SHIP_COST && !m.at(p.shipyard).isOccupied())
        {
        	Log.log("Producing new ship.");
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