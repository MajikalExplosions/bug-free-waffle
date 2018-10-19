// This Java API uses camelCase instead of the snake_case as documented in the API docs.
//     Otherwise the names of methods are consistent.

import java.util.ArrayList;
import java.util.Random;

public class MyBot {
    public static void main(String[] args) {
        Config config = new Config(/*16, */100, 250, 750, 300);
        Game game = new Game();
        Random rng = getRNG(args);
        Plantain bot = new Plantain(rng, game, config);
        bot.initialize();
        
        game.ready("Gros Michel");

        Log.log("Bot created.\nID: " + game.myId + "\n");

        while(true) {
            game.updateFrame();
            Log.log("This player's ships: ");
            for (Ship s : game.me.ships.values()) {
                Log.log("S:" + s.id);
            }
            bot.runTurn(game.me, game.gameMap);
        }
    }

    private static Random getRNG(String[] args) {
        long rngSeed;
        if (args.length > 1) {
            rngSeed = Integer.parseInt(args[1]);
        } else {
            rngSeed = System.nanoTime();
        }
        return new Random(rngSeed);
    }
}
