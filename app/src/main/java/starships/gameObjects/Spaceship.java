package starships.gameObjects;

import starships.*;

public class Spaceship extends GameObject {

    private final int pointsWhenDestroyed;
    private final int bulletsPerShot;
    private final int shotsFired;

    public Spaceship(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, double speed, double rotationDegrees, double damage, double health, boolean isVisible, int pointsWhenDestroyed, int bulletsPerShot, int shotsFired) {
        super(id, ObjectType.SPACESHIP, style, ObjectShape.TRIANGULAR, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
        this.pointsWhenDestroyed = pointsWhenDestroyed;
        this.bulletsPerShot = bulletsPerShot;
        this.shotsFired = shotsFired;
    }

    public Spaceship move(Vector direction, int speed){
        return new Spaceship(super.getId(), super.getStyle(), super.getSize(), super.getPosition(), direction, speed, super.getRotationDegrees(), super.getDamage(), super.getHealth(), super.isVisible(), pointsWhenDestroyed, bulletsPerShot, shotsFired);
    }

    public Bullet shoot(){
        return new Bullet(super.getId() + shotsFired, ObjectStyle.BULLET, new ObjectSize(10, 5), super.getPosition(), super.getDirection(), 10, super.getRotationDegrees(), 5, true);
    }

    public Spaceship rotate(int rotationDegrees){
        return new Spaceship(super.getId(), super.getStyle(), super.getSize(), super.getPosition(), super.getDirection(), super.getSpeed(), rotationDegrees, super.getDamage(), super.getHealth(), super.isVisible(), pointsWhenDestroyed, bulletsPerShot, shotsFired);
    }

    public Spaceship resetPosition(){
        return new Spaceship(super.getId(), super.getStyle(), super.getSize(), new Vector(300, 300), new Vector(0, 1), 0, 180, super.getDamage(), super.getHealth(), super.isVisible(), pointsWhenDestroyed, bulletsPerShot, shotsFired);
    }

    public int getPointsWhenDestroyed() {
        return pointsWhenDestroyed;
    }

    public int getBulletsPerShot() {
        return bulletsPerShot;
    }
}
