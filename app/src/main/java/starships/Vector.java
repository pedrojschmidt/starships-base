package starships;

public class Vector {

    private final double x;
    private final double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector addVectors(Vector vector){
        return new Vector(x + vector.getX(), y + vector.getY());
    }

    public Vector setX(double x){
        return new Vector(x, y);
    }

    public Vector setY(double y){
        return new Vector(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
