package starships;

public class Vector {

    private final int x;
    private final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector addVectors(Vector vector){
        return new Vector(x + vector.getX(), y + vector.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
