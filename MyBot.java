import hlt.*;
import NeuralNet.*;

import java.util.ArrayList;
import java.util.Random;

public class MyBot {
    public static void main(String[] args) {
        Random rng = new Random();
        Config config = new Config(/*16, */randomInt(rng, 75, 125), randomInt(rng, 100, 250), randomInt(rng, 300, 750), randomInt(rng, 50, 500));
        rng = getRNG(args);
        Game game = new Game();
        Plantain bot = new Plantain(rng, game, config);
        bot.initialize();

        game.ready("Gros Michel " + bot.BOT_VERSION);

        Log.log("\nID: " + game.myId + "\n");

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
        rngSeed = 1;
        Log.log("Random Seed: " + rngSeed);
        return new Random(rngSeed);
    }

    private static int randomInt(Random r, int min, int max) {
        return r.nextInt(max - min) + min;
    }
}
