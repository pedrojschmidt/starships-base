package starships.gameObjects;

import starships.*;

import java.util.Random;

public class Bullet extends GameObject {

    private String spaceshipId;

    public Bullet(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, double speed, double rotationDegrees, double damage, boolean isVisible, String spaceshipId) {
        super(id, ObjectType.BULLET, style, ObjectShape.RECTANGULAR, size, position, direction, speed, rotationDegrees, damage, 1, isVisible);
        this.spaceshipId = spaceshipId;
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

    public void shoot(Spaceship spaceship){
        Random random = new Random();
//        if (spaceship.canShoot()){
//            setDirection(spaceship.getRotation());
//            setRotation(spaceship.getRotation());
//            setHiding(false);
//            setxPosition(spaceship.getxPosition()+18);
//            setyPosition(spaceship.getyPosition());
//            double n = random.nextDouble(5, 15);
//            setHeight(n*3);
//            setWidth(n);
//            setDamage((int) (n*6));
//            spaceship.shootsBullet();
//        }
    }

    @Override
    public boolean isInBounds() {
        //lo cambio para que llegue a salir por completo de la pantalla
        return getPosition().getX() > (0-getSize().getHeight()) && getPosition().getX() < (800+getSize().getHeight()) && getPosition().getY() > (0-getSize().getHeight()) && getPosition().getY() < (800+getSize().getHeight());
    }

    public void removeHealth(){
        super.setInitialHealth(0);
    }

    public String getSpaceshipId() {
        return spaceshipId;
    }
}
