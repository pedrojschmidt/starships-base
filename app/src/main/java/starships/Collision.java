package starships;

import starships.gameObjects.Asteroid;
import starships.gameObjects.Bullet;
import starships.gameObjects.Spaceship;

import java.util.List;
import java.util.Objects;

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
        if (Objects.equals(spaceship.getId(), bullet.getSpaceshipId())) return;
        Player player = getPlayerFromPlayerId(spaceship.getPlayerId());
        bullet.setVisible(false);
        bullet.setPosition(new Vector(-100, -100)); // para que desaparezca
        spaceship.reduceHealth(bullet.getDamage());
        if (spaceship.getActualHealth() < 0) {
            Player player2 = getPlayerFromSpaceshipId(bullet.getSpaceshipId());
            player2.addPoints(spaceship.getPointsWhenDestroyed());
            player.removeLife();
            if (player.getLives() > 0) {
                spaceship.resetPosDirRotSpdHlth();
            }
        }
        if (player.getLives() <= 0){
            player.setAlive(false);
            spaceship.setVisible(false);
            spaceship.setPosition(new Vector(-100, -100)); // para que desaparezca
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
        Player player = getPlayerFromSpaceshipId(bullet.getSpaceshipId());
        bullet.setVisible(false);
        bullet.setPosition(new Vector(-100, -100)); // para que desaparezca
        asteroid.reduceHealth(bullet.getDamage());
        if (asteroid.getActualHealth() < 0){
            player.addPoints(asteroid.getPointsWhenDestroyed());
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
        Player player = getPlayerFromPlayerId(spaceship.getPlayerId());
        asteroid.setVisible(false);
        spaceship.reduceHealth(asteroid.getDamage());
        if (spaceship.getActualHealth() < 0) {
            player.removeLife();
            if (player.getLives() > 0) {
                spaceship.resetPosDirRotSpdHlth();
            }
        }
        if (player.getLives() <= 0){
            player.setAlive(false);
            spaceship.setVisible(false);
            spaceship.setPosition(new Vector(-100, -100)); // para que desaparezca
        }
    }

    private Player getPlayerFromPlayerId(String playerId){
        for (Player player : players){
            if (playerId.equals(player.getId())){
                return player;
            }
        }
        return null;
    }
    private Player getPlayerFromSpaceshipId(String spaceshipId){
        String playerId = "";
        for (GameObject gameObject : objects){
            if (gameObject.getType() == ObjectType.SPACESHIP && gameObject.getId().equals(spaceshipId)) {
                Spaceship spaceship = (Spaceship) gameObject;
                playerId = spaceship.getPlayerId();
            }
        }
        return getPlayerFromPlayerId(playerId);
    }


    public GameObject getObject1() {
        return object1;
    }

    public GameObject getObject2() {
        return object2;
    }
}
