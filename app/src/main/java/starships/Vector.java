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

    public Vector setX(int x){
        return new Vector(x, y);
    }

    public Vector setY(int y){
        return new Vector(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
