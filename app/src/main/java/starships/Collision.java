package starships;

import starships.gameObjects.Asteroid;
import starships.gameObjects.Bullet;
import starships.gameObjects.Spaceship;

import java.util.List;

public class Collision {

    private final GameObject object1;
    private final GameObject object2;
    private final List<GameObject> objects;
    private final List<Player> players;

    public Collision(GameObject object1, GameObject object2, List<GameObject> objects, List<Player> players) {
        this.object1 = object1;
        this.object2 = object2;
        this.objects = objects;
        this.players = players;
        handleCollision();
    }

    public void handleCollision(){
        if ((object1.getType() == ObjectType.BULLET && object2.getType() == ObjectType.SPACESHIP) || (object2.getType() == ObjectType.BULLET && object1.getType() == ObjectType.SPACESHIP)){
            //bullet and ship collision
            bulletShipCollision();
        }
        else if ((object1.getType() == ObjectType.BULLET && object2.getType() == ObjectType.ASTEROID) || (object2.getType() == ObjectType.BULLET && object1.getType() == ObjectType.ASTEROID)){
            //bullet and meteor collision
            bulletAsteroidCollision();
        }
        else if ((object1.getType() == ObjectType.SPACESHIP && object2.getType() == ObjectType.ASTEROID) || (object2.getType() == ObjectType.SPACESHIP && object1.getType() == ObjectType.ASTEROID)){
            //ship and meteor collision
            shipAsteroidCollision();
        }
    }

    public void bulletShipCollision(){
        Bullet bullet;
        Spaceship spaceship;
        if (object1.getType() == ObjectType.BULLET){
            bullet = (Bullet) object1;
            spaceship = (Spaceship) object2;
        }
        else{
            bullet = (Bullet) object2;
            spaceship = (Spaceship) object1;
        }
        if (Objects.equals(spaceship.getId(), bullet.getShipId())) return;
        Player player = getPlayer(spaceship.getPlayerId(), players);
        bullet.setVisible(false);
        player.removeLife();
        if (player.getLives() > 0){
            spaceship.resetShipPosition();
        }
        else{
            player.setAlive(false);
            spaceship.setVisible(false);
        }
    }

    public void bulletAsteroidCollision(){
        Bullet bullet;
        Asteroid asteroid;
        if (object1.getType() == ObjectType.BULLET){
            bullet = (Bullet) object1;
            asteroid = (Asteroid) object2;
        }
        else{
            bullet = (Bullet) object2;
            asteroid = (Asteroid) object1;
        }
        Player player = getPlayer(bullet.getShipId(), players, gameObjects);
        bullet.setVisible(false);
        asteroid.reduceHealth(bullet.getDamage());
        if (asteroid.getCurrentHealthBar() < 0){
            player.addPoints(asteroid.getPoints());
            asteroid.setVisible(false);
        }
    }

    public void shipAsteroidCollision(){
        Spaceship spaceship;
        Asteroid asteroid;
        if (object1.getType() == ObjectType.SPACESHIP){
            spaceship = (Spaceship) object1;
            asteroid = (Asteroid) object2;
        }
        else{
            spaceship = (Spaceship) object2;
            asteroid = (Asteroid) object1;
        }
        Player player = getPlayer(spaceship.getPlayerId(), players);
        asteroid.setVisible(false);
        player.removeLife();
        if (player.getLives() > 0){
            spaceship.resetShipPosition();
        }
        else{
            player.setAlive(false);
            spaceship.setVisible(false);
        }
    }

    public GameObject getObject1() {
        return object1;
    }

    public GameObject getObject2() {
        return object2;
    }
}
