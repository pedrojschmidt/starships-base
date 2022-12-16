package starships.gameObjects;

import starships.*;

public class Spaceship extends GameObject {

    private final int pointsWhenDestroyed;
    private final int bulletsPerShot;
    private final int shotsFired;
    private final String playerId;

    public Spaceship(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, double speed, double rotationDegrees, double damage, double health, boolean isVisible, int pointsWhenDestroyed, int bulletsPerShot, int shotsFired, String playerId) {
        super(id, ObjectType.SPACESHIP, style, ObjectShape.TRIANGULAR, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
        this.pointsWhenDestroyed = pointsWhenDestroyed;
        this.bulletsPerShot = bulletsPerShot;
        this.shotsFired = shotsFired;
        this.playerId = playerId;
    }

    @Override
    public void update() {
        if (getSpeed() > 0){
            double newX = getPosition().getX() +  getSpeed() * getDirection().getX();
            double newY = getPosition().getY() +  getSpeed() * getDirection().getY();
            //Solo se mueve dentro de la pantalla
            if (newX < 720 && newX > 0 && newY < 700 && newY > 0) {
                setPosition(new Vector(newX, newY));
            }
//            setSpeed(0); // esto hace que cuando suelto la tecla se deje de mover (si saco esta linea se sigue moviendo aunque suelte)
        }
    }

    public void resetPosDirRotSpdHlth(){
        if (getId().equals("spaceship-1")) {
            setPosition(new Vector(330, 350));
        } else {
            setPosition(new Vector(410, 350));
        }
        setDirection(new Vector(0, 0));
        setRotationDegrees(180);
        setSpeed(0);
        setActualHealth(getInitialHealth());
    }

    public void move(Vector direction){
        setDirection(direction);
        if (direction.getX() == 0.0 && direction.getY() == 0.0) {
            setSpeed(0.0);
        } else {
            setSpeed(4);
        }
    }

    public void rotate(double rotationDegrees){
        setRotationDegrees(getRotationDegrees() + rotationDegrees);
    }

    public Bullet shoot(){
        return new Bullet(getId() + shotsFired, ObjectStyle.BULLET, new ObjectSize(10, 5), getPosition(), getDirection(), 10, getRotationDegrees(), 5, true, getId());
    }

    public Spaceship resetPosition(){
        return new Spaceship(getId(), getStyle(), getSize(), new Vector(300, 300), new Vector(0, 1), 0, 180, getDamage(), getInitialHealth(), isVisible(), pointsWhenDestroyed, bulletsPerShot, shotsFired, playerId);
    }

    public int getPointsWhenDestroyed() {
        return pointsWhenDestroyed;
    }

    public int getBulletsPerShot() {
        return bulletsPerShot;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public String getPlayerId() {
        return playerId;
    }
}
