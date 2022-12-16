package starships.gameObjects;

import starships.*;

import java.util.Random;

public class Bullet extends GameObject {

    private final String spaceshipId;

    public Bullet(String id, ObjectStyle style, ObjectSize size, Vector position, Vector direction, double speed, double rotationDegrees, double damage, boolean isVisible, String spaceshipId) {
        super(id, ObjectType.BULLET, style, ObjectShape.RECTANGULAR, size, position, direction, speed, rotationDegrees, damage, 1, isVisible, 1);
        this.spaceshipId = spaceshipId;
    }

    public Bullet update() {
        if (isVisible()) {
            if (getSpeed() > 0){
                double newX = getPosition().getX() +  getSpeed() * getDirection().getX();
                double newY = getPosition().getY() +  getSpeed() * getDirection().getY();
                //Solo se mueve dentro de la pantalla
                if (isInBounds()){
                    return setPosition(new Vector(newX, newY));
                }else{
                    return setVisible(false);
                }
            }
        }
        return this;
    }

    public void shoot(Spaceship spaceship){
//        Random random = new Random();
//        if (spaceship.canShoot()){
            setDirectionFromRotation(spaceship.getRotationDegrees());
            setRotationDegrees(spaceship.getRotationDegrees());
            setVisible(true);
            setPosition(new Vector(spaceship.getPosition().getX()+20, spaceship.getPosition().getY()));
//            spaceship.shoot();
//        }
    }

    @Override
    public boolean isInBounds() {
        //lo cambio para que llegue a salir por completo de la pantalla
        return getPosition().getX() > (0-getSize().getHeight()) && getPosition().getX() < (800+getSize().getHeight()) && getPosition().getY() > (0-getSize().getHeight()) && getPosition().getY() < (800+getSize().getHeight());
    }

    public Bullet setDirectionFromRotation(double degrees){
        double degreesBetween0and360 = degrees;
        while (degreesBetween0and360 > 360) {
            degreesBetween0and360 = degreesBetween0and360 - 360;
        }
        double radians = Math.toRadians(degreesBetween0and360);
        if (degreesBetween0and360 >= 0 && degreesBetween0and360 <= 90) {
            //De esta manera, el modulo del vector siempre es 1
            double pow = Math.pow(Math.tan(radians), 2);
            double x = Math.sqrt(pow / (1 + pow)); // -x
            double y = x / Math.tan(radians); // +y
            return setDirection(new Vector(-x, y));
        } else if (degreesBetween0and360 > 90 && degreesBetween0and360 <= 180) {
            double tan = Math.tan(radians - Math.toRadians(90));
            double pow = Math.pow(tan, 2);
            double y = Math.sqrt(pow / (1 + pow)); // -y
            double x = y / tan; // -x
            return setDirection(new Vector(-x, -y));
        } else if (degreesBetween0and360 > 180 && degreesBetween0and360 <= 270) {
            double tan = Math.tan(radians - Math.toRadians(180));
            double pow = Math.pow(tan, 2);
            double x = Math.sqrt(pow / (1 + pow)); // +x
            double y = x / tan; // -y
            return setDirection(new Vector(x, -y));
        } else if (degreesBetween0and360 > 270) {
            double tan = Math.tan(radians - Math.toRadians(270));
            double pow = Math.pow(tan, 2);
            double y = Math.sqrt(pow / (1 + pow)); // +y
            double x = y / tan; // +x
            return setDirection(new Vector(x, y));
        }
        return this;
    }

    public Bullet setPosition(Vector position) {
        return new Bullet(getId(), getStyle(), getSize(), position, getDirection(), getSpeed(), getRotationDegrees(), getDamage(), isVisible(), spaceshipId);
    }

    public Bullet setDirection(Vector direction) {
        return new Bullet(getId(), getStyle(), getSize(), getPosition(), direction, getSpeed(), getRotationDegrees(), getDamage(), isVisible(), spaceshipId);
    }

    public Bullet setSpeed(double speed) {
        return new Bullet(getId(), getStyle(), getSize(), getPosition(), getDirection(), speed, getRotationDegrees(), getDamage(), isVisible(), spaceshipId);
    }

    public Bullet setRotationDegrees(double rotationDegrees) {
        return new Bullet(getId(), getStyle(), getSize(), getPosition(), getDirection(), getSpeed(), rotationDegrees, getDamage(), isVisible(), spaceshipId);
    }

    public Bullet setVisible(boolean visible) {
        return new Bullet(getId(), getStyle(), getSize(), getPosition(), getDirection(), getSpeed(), getRotationDegrees(), getDamage(), visible, spaceshipId);
    }

    public void removeHealth(){
        super.setInitialHealth(0);
    }

    public String getSpaceshipId() {
        return spaceshipId;
    }
}
