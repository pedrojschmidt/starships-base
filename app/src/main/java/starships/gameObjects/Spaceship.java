package starships.gameObjects;

import starships.*;

public class Spaceship extends GameObject {

    private final Player player;
    private final int pointsWhenDestroyed;
    private final int bulletsPerShot;

    public Spaceship(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, int speed, int rotationDegrees, int damage, int health, boolean isVisible, Player player, int pointsWhenDestroyed, int bulletsPerShot) {
        super(id, ObjectType.SPACESHIP, style, ObjectShape.TRIANGULAR, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
        this.player = player;
        this.pointsWhenDestroyed = pointsWhenDestroyed;
        this.bulletsPerShot = bulletsPerShot;
    }

    public Spaceship move(Vector direction, int speed){
        return new Spaceship(super.getId(), super.getStyle(), super.getSize(), super.getPosition(), direction, speed, super.getRotationDegrees(), super.getDamage(), super.getHealth(), super.isVisible(), player, pointsWhenDestroyed, bulletsPerShot);
    }

    public Spaceship rotate(int rotationDegrees){
        return new Spaceship(super.getId(), super.getStyle(), super.getSize(), super.getPosition(), super.getDirection(), super.getSpeed(), rotationDegrees, super.getDamage(), super.getHealth(), super.isVisible(), player, pointsWhenDestroyed, bulletsPerShot);
    }

    public Spaceship resetPosition(){
        return new Spaceship(super.getId(), super.getStyle(), super.getSize(), new Vector(300, 300), new Vector(0, 1), 0, 180, super.getDamage(), super.getHealth(), super.isVisible(), player, pointsWhenDestroyed, bulletsPerShot);
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
