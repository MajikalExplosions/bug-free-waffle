public class Shipyard extends Entity {
    public Shipyard(int ownerid, Position position) {
        super(ownerid, -1, position);
    }

    public Command spawn() {
        return Command.spawnShip();
    }
}
