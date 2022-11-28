package starships.gameObjects;

import starships.*;

public class Spaceship extends GameObject {

    public Spaceship(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, Vector speed, int rotationDegrees, int damage, int health, boolean isVisible) {
        super(id, ObjectType.SPACESHIP, style, ObjectShape.TRIANGULAR, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
    }

    public Spaceship resetPosition(){
        return (Spaceship) super.changePosDirRot(new Vector(300, 300), new Vector(0, 1), 180);
    }
}
