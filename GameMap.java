import java.util.ArrayList;

public class GameMap {
    public int width;
    public int height;
    public MapCell[][] cells;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;

        cells = new MapCell[height][];
        for (int y = 0; y < height; ++y) {
            cells[y] = new MapCell[width];
        }
    }

    public MapCell at(Position position) {
        Position normalized = normalize(position);
        return cells[normalized.y][normalized.x];
    }

    public MapCell at(Entity entity) {
        return at(entity.position);
    }

    public int calculateDistance(Position source, Position target) {
        Position normalizedSource = normalize(source);
        Position normalizedTarget = normalize(target);

        int dx = Math.abs(normalizedSource.x - normalizedTarget.x);
        int dy = Math.abs(normalizedSource.y - normalizedTarget.y);

        int toroidal_dx = Math.min(dx, width - dx);
        int toroidal_dy = Math.min(dy, height - dy);

        return toroidal_dx + toroidal_dy;
    }

    public Position normalize(Position position) {
        int x = ((position.x % width) + width) % width;
        int y = ((position.y % height) + height) % height;
        return new Position(x, y);
    }

    public ArrayList<Direction> getUnsafeMoves(Position source, Position destination) {
        ArrayList<Direction> possibleMoves = new ArrayList<>();

        Position normalizedSource = normalize(source);
        Position normalizedDestination = normalize(destination);

        int dx = Math.abs(normalizedSource.x - normalizedDestination.x);
        int dy = Math.abs(normalizedSource.y - normalizedDestination.y);
        int wrapped_dx = width - dx;
        int wrapped_dy = height - dy;

        if (normalizedSource.x < normalizedDestination.x) {
            possibleMoves.add(dx > wrapped_dx ? Direction.WEST : Direction.EAST);
        } else if (normalizedSource.x > normalizedDestination.x) {
            possibleMoves.add(dx < wrapped_dx ? Direction.WEST : Direction.EAST);
        }

        if (normalizedSource.y < normalizedDestination.y) {
            possibleMoves.add(dy > wrapped_dy ? Direction.NORTH : Direction.SOUTH);
        } else if (normalizedSource.y > normalizedDestination.y) {
            possibleMoves.add(dy < wrapped_dy ? Direction.NORTH : Direction.SOUTH);
        }

        return possibleMoves;
    }

    public Direction naiveNavigate(Ship ship, Position destination) {
        // getUnsafeMoves normalizes for us
        for (Direction direction : getUnsafeMoves(ship.position, destination)) {
            Position targetPos = ship.position.directionalOffset(direction);
            if (!at(targetPos).isOccupied()) {
                at(targetPos).markUnsafe(ship);
                return direction;
            }
        }

        return Direction.STILL;
    }

    void _update() {
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                cells[y][x].ship = null;
            }
        }

        int updateCount = Input.readInput().getInt();

        for (int i = 0; i < updateCount; ++i) {
            Input input = Input.readInput();
            int x = input.getInt();
            int y = input.getInt();

            cells[y][x].halite = input.getInt();
        }
    }

    static GameMap _generate() {
        Input mapInput = Input.readInput();
        int width = mapInput.getInt();
        int height = mapInput.getInt();

        GameMap map = new GameMap(width, height);

        for (int y = 0; y < height; ++y) {
            Input rowInput = Input.readInput();

            for (int x = 0; x < width; ++x) {
                int halite = rowInput.getInt();
                map.cells[y][x] = new MapCell(new Position(x, y), halite);
            }
        }

        return map;
    }
}
