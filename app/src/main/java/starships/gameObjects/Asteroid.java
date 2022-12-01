package starships.gameObjects;

import starships.*;

public class Asteroid extends GameObject {

    private final int pointsWhenDestroyed;

    public Asteroid(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, double speed, double rotationDegrees, double damage, double health, boolean isVisible, int pointsWhenDestroyed) {
        super(id, ObjectType.ASTEROID, style, ObjectShape.CIRCULAR, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
        this.pointsWhenDestroyed = pointsWhenDestroyed;
    }

    @Override
    public void update() {
        if (isVisible()) {
            if (getSpeed() > 0){
                double newX = getPosition().getX() +  getSpeed() * getDirection().getX();
                double newY = getPosition().getY() +  getSpeed() * getDirection().getY();
                //Solo se mueve dentro de la pantalla
                if (isInBounds()){
                    setPosition(new Vector(newX, newY));
                    setRotationDegrees(getRotationDegrees() + 1);
                }else{
                    setVisible(false);
                }
            }
        }
    }

    @Override
    public boolean isInBounds() {
        //lo cambio para que llegue a salir por completo de la pantalla
        return getPosition().getX() > (0-getSize().getHeight()) && getPosition().getX() < (800+getSize().getHeight()) && getPosition().getY() > (0-getSize().getHeight()) && getPosition().getY() < (800+getSize().getHeight());
    }

    public int getPointsWhenDestroyed() {
        return pointsWhenDestroyed;
    }
}
