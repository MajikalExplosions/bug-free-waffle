public class Ship extends Entity {
    public int halite;

    public Ship(int ownerid, int entityid, Position position, int halite) {
        super(ownerid, entityid, position);
        this.halite = halite;
    }

    public boolean isFull() {
        return halite >= Constants.MAX_HALITE;
    }

    public Command makeDropoff() {
        return Command.transformShipIntoDropoffSite(id);
    }

    public Command move(Direction direction) {
        Log.log("Moving ship " + id);
        return Command.move(id, direction);
    }

    public Command stayStill() {
        return Command.move(id, Direction.STILL);
    }

    static Ship _generate(int ownerid) {
        Input input = Input.readInput();
        int shipId = input.getInt();
        int x = input.getInt();
        int y = input.getInt();
        int halite = input.getInt();
        Log.log("Generated ship " + shipId + "@" + x + ", " + y + " (Player " + ownerid + ")");
        return new Ship(ownerid, shipId, new Position(x, y), halite);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Ship ship = (Ship) o;

        return halite == ship.halite;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + halite;
        return result;
    }
}
