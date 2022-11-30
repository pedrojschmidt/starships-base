package starships.gameObjects;

import starships.*;

public class Bullet extends GameObject {

    public Bullet(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, double speed, double rotationDegrees, double damage, boolean isVisible) {
        super(id, ObjectType.BULLET, style, ObjectShape.RECTANGULAR, size, position, direction, speed, rotationDegrees, damage, 1, isVisible);
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

    public void removeHealth(){
        super.setHealth(0);
    }
}
