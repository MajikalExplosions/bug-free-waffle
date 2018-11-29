public class Entity {
    public int owner;
    public int id;
    public Position position;
    public ControlQueue queue;

    public Command doNextAction() {
        return queue.pop();
    }

    public Entity(int ownerid, int entityid, Position position) {
        this.owner = ownerid;
        this.id = entityid;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (owner != entity.owner) return false;
        if (id != entity.id) return false;
        return position.equals(entity.position);
    }

    @Override
    public int hashCode() {
        int result = owner;
        result = 31 * result + id;
        result = 31 * result + position.hashCode();
        return result;
    }
}
