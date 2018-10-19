public class Dropoff extends Entity {

    public Dropoff(int ownerid, Position position) {
        super(ownerid, -1, position);
    }
    
    static Dropoff _generate(int playerid) {
        Input input = Input.readInput();

        int dropoffid = input.getInt();
        int x = input.getInt();
        int y = input.getInt();

        return new Dropoff(playerid, new Position(x, y));
    }
}
