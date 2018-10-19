import java.util.LinkedHashMap;
import java.util.Map;

public class Player {
    public int id;
    public Shipyard shipyard;
    public int halite;
    public Map<Integer, Ship> ships = new LinkedHashMap<>();//ID for entity, ship
    public Map<Integer, Dropoff> dropoffs = new LinkedHashMap<>();//Id for dropoff, ship

    private Player(int id, Shipyard shipyard) {
        this.id = id;
        this.shipyard = shipyard;
    }

    void _update(int numShips, int numDropoffs, int halite) {
        this.halite = halite;

        ships.clear();
        for (int i = 0; i < numShips; ++i) {
            Ship ship = Ship._generate(id);
            ships.put(ship.id, ship);
        }

        dropoffs.clear();
        for (int i = 0; i < numDropoffs; ++i) {
            Dropoff dropoff = Dropoff._generate(id);
            dropoffs.put(dropoff.id, dropoff);
        }
    }

    static Player _generate() {
        Input input = Input.readInput();

        int playerId = input.getInt();
        int shipyard_x = input.getInt();
        int shipyard_y = input.getInt();

        return new Player(playerId, new Shipyard(playerId, new Position(shipyard_x, shipyard_y)));
    }
}
