import NeuralNet.*;

import java.util.ArrayList;
import java.util.Random;

public class MyBot {
    public static void main(String[] args) {
        Random rng = new Random();
        Config config = new Config(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        //Config config = new Config(/*16, */randomInt(rng, 75, 125), randomInt(rng, 100, 250), randomInt(rng, 300, 750), randomInt(rng, 50, 500));
        rng = getRNG(args);
        Game game = new Game();
        Plantain bot = new Plantain(rng, game, config);
        bot.initialize();

        game.ready("Gros Michel v" + bot.BOT_VERSION + " " + bot.VERSION_NAME);

        Log.log("\nID: " + game.myId + "\n");
        boolean nextTurn = true;
        while(nextTurn) {
            game.updateFrame();
            nextTurn = bot.runTurn(game.me, game.gameMap);
        }
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        if (game.myId == 0) {
            if (game.players.get(0).halite > game.players.get(1).halite) System.out.println(0);
        }
        else if (game.myId == 1) {
            if (game.players.get(1).halite > game.players.get(0).halite) System.out.println(0);
        }
        else System.out.println(1);
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
