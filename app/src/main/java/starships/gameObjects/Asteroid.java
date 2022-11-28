package starships.gameObjects;

import starships.*;

public class Asteroid extends GameObject {

    private final int pointsWhenDestroyed;

    public Asteroid(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, int speed, int rotationDegrees, int damage, int health, boolean isVisible, int pointsWhenDestroyed) {
        super(id, ObjectType.ASTEROID, style, ObjectShape.CIRCULAR, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
        this.pointsWhenDestroyed = pointsWhenDestroyed;
    }

}
