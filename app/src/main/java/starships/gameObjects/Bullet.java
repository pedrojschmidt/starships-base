package starships.gameObjects;

import starships.*;

public class Bullet extends GameObject {

    public Bullet(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, double speed, double rotationDegrees, double damage, boolean isVisible) {
        super(id, ObjectType.BULLET, style, ObjectShape.RECTANGULAR, size, position, direction, speed, rotationDegrees, damage, 1, isVisible);
    }

    public void removeHealth(){
        super.setHealth(0);
    }
}
