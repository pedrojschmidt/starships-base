package starships.gameObjects;

import starships.*;

public class Asteroid extends GameObject {

    private final int pointsWhenDestroyed;

    public Asteroid(int pointsWhenDestroyed, String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, int speed, int rotationDegrees, int damage, int health, boolean isVisible) {
        super(id, ObjectType.ASTEROID, style, ObjectShape.CIRCULAR, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
        this.pointsWhenDestroyed = pointsWhenDestroyed;
    }

}
