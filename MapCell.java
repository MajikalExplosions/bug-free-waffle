public class MapCell {
    public Position position;
    public int halite;
    public Ship ship;
    public Entity structure;

    public MapCell(Position position, int halite) {
        this.position = position;
        this.halite = halite;
    }

    public boolean isEmpty() {
        return ship == null && structure == null;
    }

    public boolean isOccupied() {
        return ship != null;
    }

    public boolean hasStructure() {
        return structure != null;
    }

    public void markUnsafe(Ship ship) {
        this.ship = ship;
    }
}
