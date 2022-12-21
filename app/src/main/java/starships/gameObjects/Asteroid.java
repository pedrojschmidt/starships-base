package starships.gameObjects;

import starships.*;

public class Asteroid extends GameObject {

    private final int pointsWhenDestroyed;

    public Asteroid(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, double speed, double rotationDegrees, double damage, double health, boolean isVisible, double actualHealth, int pointsWhenDestroyed) {
        super(id, ObjectType.ASTEROID, style, ObjectShape.CIRCULAR, size, position, direction, speed, rotationDegrees, damage, health, isVisible, actualHealth);
        this.pointsWhenDestroyed = pointsWhenDestroyed;
    }

    public Asteroid update() {
        if (isVisible()) {
            if (getSpeed() > 0){
                double newX = getPosition().getX() +  getSpeed() * getDirection().getX();
                double newY = getPosition().getY() +  getSpeed() * getDirection().getY();
                //Solo se mueve dentro de la pantalla
                if (isInBounds()){
                    Asteroid asteroid = setPosition(new Vector(newX, newY));
                    return asteroid.setRotationDegrees(getRotationDegrees() + 1);
                }else{
                    return setVisible(false);
                }
            }
        }
        return this;
    }

    public Asteroid setActualHealth(double actualHealth){
        return new Asteroid(getId(), getStyle(), getSize(), getPosition(), getDirection(), getSpeed(), getRotationDegrees(), getDamage(), getInitialHealth(), isVisible(), actualHealth, pointsWhenDestroyed);
    }

    public Asteroid reduceHealth(double amount){
        return new Asteroid(getId(), getStyle(), getSize(), getPosition(), getDirection(), getSpeed(), getRotationDegrees(), getDamage(), getInitialHealth(), isVisible(), getActualHealth()-amount, pointsWhenDestroyed);
    }

    @Override
    public boolean isInBounds() {
        //lo cambio para que llegue a salir por completo de la pantalla
        return getPosition().getX() > (0-getSize().getHeight()) && getPosition().getX() < (800+getSize().getHeight()) && getPosition().getY() > (0-getSize().getHeight()) && getPosition().getY() < (800+getSize().getHeight());
    }

    public Asteroid setPosition(Vector position) {
        return new Asteroid(getId(), getStyle(), getSize(), position, getDirection(), getSpeed(), getRotationDegrees(), getDamage(), getInitialHealth(), isVisible(), getActualHealth(), pointsWhenDestroyed);
    }

    public Asteroid setDirection(Vector direction) {
        return new Asteroid(getId(), getStyle(), getSize(), getPosition(), direction, getSpeed(), getRotationDegrees(), getDamage(), getInitialHealth(), isVisible(), getActualHealth(), pointsWhenDestroyed);
    }

    public Asteroid setSpeed(double speed) {
        return new Asteroid(getId(), getStyle(), getSize(), getPosition(), getDirection(), speed, getRotationDegrees(), getDamage(), getInitialHealth(), isVisible(), getActualHealth(), pointsWhenDestroyed);
    }

    public Asteroid setRotationDegrees(double rotationDegrees) {
        return new Asteroid(getId(), getStyle(), getSize(), getPosition(), getDirection(), getSpeed(), rotationDegrees, getDamage(), getInitialHealth(), isVisible(), getActualHealth(), pointsWhenDestroyed);
    }

    public Asteroid setVisible(boolean visible) {
        return new Asteroid(getId(), getStyle(), getSize(), getPosition(), getDirection(), getSpeed(), getRotationDegrees(), getDamage(), getInitialHealth(), visible, getActualHealth(), pointsWhenDestroyed);
    }

    public int getPointsWhenDestroyed() {
        return pointsWhenDestroyed;
    }
}
