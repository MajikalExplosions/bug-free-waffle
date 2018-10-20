// This Java API uses camelCase instead of the snake_case as documented in the API docs.
//     Otherwise the names of methods are consistent.

import java.util.ArrayList;
import java.util.Random;

public class MyBot {
    public static void main(String[] args) {
        Config config = new Config(/*16, */100, 175, 450, 75);
        Game game = new Game();
        Random rng = getRNG(args);
        Plantain bot = new Plantain(rng, game, config);
        bot.initialize();
        
        game.ready("Gros Michel");

        Log.log("Bot created.\nID: " + game.myId + "\n");

        while(true) {
            game.updateFrame();
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
