import java.util.ArrayList;
import java.util.Collection;

public class Game {
    public int turnNumber;
    public int myId;
    public ArrayList<Player> players = new ArrayList<>();
    public Player me;
    public GameMap gameMap;

    public Game() {
        Constants.populateConstants(Input.readLine());

        Input input = Input.readInput();
        int numPlayers = input.getInt();
        myId = input.getInt();

        Log.open(myId);

        for (int i = 0; i < numPlayers; ++i) {
            players.add(Player._generate());
        }
        me = players.get(myId);
        gameMap = GameMap._generate();
    }

    public void ready(String name) {
        System.out.println(name);
    }

    public void updateFrame() {
        turnNumber = Input.readInput().getInt();
        Log.log("=============== TURN " + turnNumber + " ================");

        for (int i = 0; i < players.size(); ++i) {
            Input input = Input.readInput();

            int currentPlayerId = input.getInt();
            int numShips = input.getInt();
            int numDropoffs = input.getInt();
            int halite = input.getInt();

            players.get(currentPlayerId)._update(numShips, numDropoffs, halite);
        }

        gameMap._update();

        for (Player player : players) {
            for (Ship ship : player.ships.values()) {
                gameMap.at(ship).markUnsafe(ship);
            }

            gameMap.at(player.shipyard).structure = player.shipyard;

            for (Dropoff dropoff : player.dropoffs.values()) {
                gameMap.at(dropoff).structure = dropoff;
            }
        }
    }

    public void endTurn(Collection<Command> commands) {
        for (Command command : commands) {
            System.out.print(command.command);
            System.out.print(' ');
        }
        System.out.println();
    }
}
