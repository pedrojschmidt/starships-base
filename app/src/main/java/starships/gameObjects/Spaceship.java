package starships.gameObjects;

import starships.*;

public class Spaceship extends GameObject {

    private final Player player;
    private final int pointsWhenDestroyed;
    private final int bulletsPerShot;

    public Spaceship(Player player, int pointsWhenDestroyed, String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, int speed, int rotationDegrees, int damage, int health, boolean isVisible, int bulletsPerShot) {
        super(id, ObjectType.SPACESHIP, style, ObjectShape.TRIANGULAR, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
        this.player = player;
        this.pointsWhenDestroyed = pointsWhenDestroyed;
        this.bulletsPerShot = bulletsPerShot;
    }

//    public Spaceship newSpaceship(){
//
//    }

    public Spaceship move(Vector direction, int speed){
        return (Spaceship) super.changeDirSpd(direction, speed);
    }

    public Spaceship rotate(int rotationDegrees){
        return (Spaceship) super.setRotation(rotationDegrees);
    }

    public Spaceship resetPosition(){
        return (Spaceship) super.changePosDirRot(new Vector(300, 300), new Vector(0, 1), 180);
    }



    public Player getPlayer() {
        return player;
    }

    public int getPointsWhenDestroyed() {
        return pointsWhenDestroyed;
    }

    public int getBulletsPerShot() {
        return bulletsPerShot;
    }
}
