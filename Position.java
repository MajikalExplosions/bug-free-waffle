public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Position directionalOffset(Direction d) {
        int dx;
        int dy;

        switch (d) {
            case NORTH:
                dx = 0;
                dy = -1;
                break;
            case SOUTH:
                dx = 0;
                dy = 1;
                break;
            case EAST:
                dx = 1;
                dy = 0;
                break;
            case WEST:
                dx = -1;
                dy = 0;
                break;
            case STILL:
                dx = 0;
                dy = 0;
                break;
            default:
                throw new IllegalStateException("Unknown direction " + d);
        }

        return new Position(x + dx, y + dy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
