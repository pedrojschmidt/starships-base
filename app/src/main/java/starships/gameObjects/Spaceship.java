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

    @Override
    public void update() {
        if (getSpeed() > 0){
            double newX = getPosition().getX() +  getSpeed() * getDirection().getX();
            double newY = getPosition().getY() +  getSpeed() * getDirection().getY();
            //Solo se mueve dentro de la pantalla
            if (isInBounds()){
                setPosition(new Vector(newX, newY));
            }
        }
    }

    public Spaceship move(Vector direction, int speed){
        return new Spaceship(getId(), getStyle(), getSize(), getPosition(), direction, speed, getRotationDegrees(), getDamage(), getHealth(), isVisible(), pointsWhenDestroyed, bulletsPerShot, shotsFired);
    }

    public Bullet shoot(){
        return new Bullet(getId() + shotsFired, ObjectStyle.BULLET, new ObjectSize(10, 5), getPosition(), getDirection(), 10, getRotationDegrees(), 5, true);
    }

    public Spaceship rotate(int rotationDegrees){
        return new Spaceship(getId(), getStyle(), getSize(), getPosition(), getDirection(), getSpeed(), rotationDegrees, getDamage(), getHealth(), isVisible(), pointsWhenDestroyed, bulletsPerShot, shotsFired);
    }

    public Spaceship resetPosition(){
        return new Spaceship(getId(), getStyle(), getSize(), new Vector(300, 300), new Vector(0, 1), 0, 180, getDamage(), getHealth(), isVisible(), pointsWhenDestroyed, bulletsPerShot, shotsFired);
    }

    public int getPointsWhenDestroyed() {
        return pointsWhenDestroyed;
    }

    public int getBulletsPerShot() {
        return bulletsPerShot;
    }
}
