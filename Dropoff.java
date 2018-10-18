public class Dropoff extends Entity {
    public Dropoff(int owner, int eid, Position position) {
        super(owner, id, position);
    }

    static Dropoff _generate(int playerid) {
        final Input input = Input.readInput();

        int dropoffid = input.getInt();
        int x = input.getInt();
        int y = input.getInt();

        return new Dropoff(playerid, dropoffId, new Position(x, y));
    }
}
