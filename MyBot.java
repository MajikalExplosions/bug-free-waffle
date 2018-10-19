// This Java API uses camelCase instead of the snake_case as documented in the API docs.
//     Otherwise the names of methods are consistent.

import java.util.ArrayList;
import java.util.Random;

public class MyBot {
    public static void main(String[] args) {
        Random rng = getRNG();
        Game game = new Game();
        Plantain bot = new Plantain(rng, game);
        // At this point "game" variable is populated with initial map data.
        // This is a good place to do computationally expensive start-up pre-processing.
        // As soon as you call "ready" function below, the 2 second per turn timer will start.
        game.ready("Gros Michel");

        Log.log("Bot created.\nID: " + game.myId + "\nRNG Seed: " + rngSeed + "\n");

        while(true) {
            game.updateFrame();
            plantain.runTurn(game.me, game.gameMap);
        }
        /*
        for (;;) {

            for (Ship ship : me.ships.values()) {
                if (gameMap.at(ship).halite < Constants.MAX_HALITE / 10 || ship.isFull()) {
                    Direction randomDirection = Direction.ALL_CARDINALS.get(rng.nextInt(4));
                    commandQueue.add(ship.move(randomDirection));
                } else {
                    commandQueue.add(ship.stayStill());
                }
            }

            if (
                game.turnNumber <= 200 &&
                me.halite >= Constants.SHIP_COST &&
                !gameMap.at(me.shipyard).isOccupied())
            {
                commandQueue.add(me.shipyard.spawn());
            }
        }
        */
    }

    private static Random getRNG() {
        long rngSeed;
        if (args.length > 1) {
            rngSeed = Integer.parseInt(args[1]);
        } else {
            rngSeed = System.nanoTime();
        }
        return new Random(rngSeed);
    }
}
