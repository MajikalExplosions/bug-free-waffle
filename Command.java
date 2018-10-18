public class Command {
    private String command;

    public Command(String c) {
        command = c;
    }

    public static Command spawnShip() {
        return new Command("g");
    }

    public static Command transformShipIntoDropoffSite(int shipid) {
        return new Command("c " + shipid);
    }

    public static Command move(int shipid, Direction direction) {
        return new Command("m " + shipid + ' ' + direction.charValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command1 = (Command) o;

        return command.equals(command1.command);
    }

    @Override
    public int hashCode() {
        return command.hashCode();
    }
}
