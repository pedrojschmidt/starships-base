package starships.gameObjects;

import starships.*;

public class Spaceship extends GameObject {

    public Spaceship(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, int speed, int rotationDegrees, int damage, int health, boolean isVisible) {
        super(id, ObjectType.SPACESHIP, style, ObjectShape.TRIANGULAR, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
    }

    public Spaceship move(Vector direction, int speed){
        return (Spaceship) super.changeDirSpd(direction, speed);
    }

    public Spaceship rotate(int rotationDegrees){
        return (Spaceship) super.setRotation(rotationDegrees);
    }

    public Spaceship resetPosition(){
        return (Spaceship) super.changePosDirRot(new Vector(300, 300), new Vector(0, 1), 180);
    }
}
