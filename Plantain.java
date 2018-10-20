import java.util.ArrayList;
import java.util.Random;

public class Plantain {

	private final String BOT_VERSION = "0.2.1 Monterey";
	public final Config CONFIG;

	private ArrayList<Command> commands;
	private Random random;
	private Game game;

	public Plantain(Random rng, Game g, Config c) {
		random = rng;
		game = g;
		CONFIG = c;
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
		monterey(me, map);
		game.endTurn(commands);
	}

	private void monterey(Player p, GameMap m) {
		//Versions 0.2.?
		for (Ship s : p.ships.values()) {
			if (s.halite > CONFIG.maxCapacityBeforeDropoff) {
				commands.add(s.move(m.naiveNavigate(s, p.shipyard.position)));
			}
            else if (m.at(s).halite < CONFIG.minHaliteAmount) {
                //Get more stuff
                int r = random.nextInt(4);
                Direction d = Direction.ALL_CARDINALS.get(r);
                while((! m.at(s.position.directionalOffset(d)).isEmpty()) && r < 8) {
                	d = Direction.ALL_CARDINALS.get(++r % 4);
                }
                commands.add(s.move(d));
                m.at(s.position.directionalOffset(d)).markUnsafe(s);
            }
            else {
                commands.add(s.stayStill());
            }
		}
		//Add_code_here
		if (game.turnNumber <= CONFIG.maxShipTurn && p.halite >= Constants.SHIP_COST && !m.at(p.shipyard).isOccupied() && p.ships.size() < CONFIG.maxShipCount) commands.add(p.shipyard.spawn());
	}

	private void eureka(Player p, GameMap m) {
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