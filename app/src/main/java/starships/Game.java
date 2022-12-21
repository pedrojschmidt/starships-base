package starships;

import starships.configuration.Configuration;
import starships.gameObjects.Asteroid;
import starships.gameObjects.Bullet;
import starships.gameObjects.Spaceship;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {

    private List<Player> players;
    private List<Asteroid> asteroids;
    private List<Bullet> bullets;
    private List<GameObject> objects;

    private Configuration configuration;

    private boolean isPaused;
    private boolean isOver;

    public Game() {
        configuration = new Configuration();
    }

    private void newGame(){
        createPlayers();
        createBullets(30);
        createAsteroids();
        fillObjects();
    }

    private void fillObjects(){
        objects = new ArrayList<>();
        for (Player player: players) {
            objects.add(player.getSpaceship());
        }
        objects.addAll(asteroids);
        objects.addAll(bullets);
    }

    public void start(){
        newGame();
        this.isPaused = false;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void reset(){
        newGame();
    }

    public void update(){
        if (!isPaused){
            if (objects != null) {
                AsteroidGenerator.generateAsteroids(objects, this, players);
                List<GameObject> objectsAux = new ArrayList<>();
                List<Bullet> bulletsAux = new ArrayList<>();
                List<Asteroid> asteroidsAux = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    GameObject gameObject = objects.get(i);
                    if (gameObject.getType() == ObjectType.SPACESHIP) {
                        Spaceship spaceship = (Spaceship) gameObject;
                        Spaceship newSpaceship = spaceship.update();
                        objectsAux.add(newSpaceship);
                        Player owner = getPlayerBySpaceship(spaceship);
                        owner.setSpaceship(newSpaceship);
                    } else if (gameObject.getType() == ObjectType.BULLET){
                        Bullet bullet = (Bullet) gameObject;
                        Bullet newBullet = bullet.update();
                        objectsAux.add(newBullet);
                        bulletsAux.add(newBullet);
                    } else if (gameObject.getType() == ObjectType.ASTEROID){
                        Asteroid asteroid = (Asteroid) gameObject;
                        Asteroid newAsteroid = asteroid.update();
                        objectsAux.add(newAsteroid);
                        asteroidsAux.add(newAsteroid);
                    }
                }
                objects = objectsAux;
                bullets = bulletsAux;
                asteroids = asteroidsAux;
            }
        }
    }

    public void updateObjects(GameObject newObject, GameObject object){
        int indexObjects = objects.indexOf(object);
        objects.set(indexObjects, newObject);
    }

    public void updateBullets(Bullet newBullet, Bullet bullet){
        int indexBullets = bullets.indexOf(bullet);
        bullets.set(indexBullets, newBullet);
    }

    public void updateAsteroids(Asteroid newAsteroid, Asteroid asteroid){
        int indexAsteroids = asteroids.indexOf(asteroid);
        asteroids.set(indexAsteroids, newAsteroid);
    }

    public void updateSpaceships(Spaceship newSpaceship, Spaceship spaceship){
        Player owner = getPlayerBySpaceship(spaceship);
        owner.setSpaceship(newSpaceship);
    }

    public Player getPlayerBySpaceship(Spaceship spaceship){
        String playerId = spaceship.getPlayerId();
        for (Player player: players) {
            if (player.getId().equals(playerId)) {
                return player;
            }
        }
        return null;
    }

    public void moveShip(int spaceshipIndex, Vector direction){
        if (spaceshipIndex < objects.size() && objects.get(spaceshipIndex).getType() == ObjectType.SPACESHIP && !isPaused){
            Spaceship spaceship = (Spaceship) objects.get(spaceshipIndex);
            Player owner = getPlayerBySpaceship(spaceship);
            Spaceship newSpaceship = spaceship.move(direction);
            objects.set(objects.indexOf(spaceship), newSpaceship);
            owner.setSpaceship(newSpaceship);
        }
    }
    public void rotateShip(int spaceshipIndex, double rotation){
        if (spaceshipIndex < objects.size() && objects.get(spaceshipIndex).getType() == ObjectType.SPACESHIP && !isPaused){
            Spaceship spaceship = (Spaceship) objects.get(spaceshipIndex);
            Player owner = getPlayerBySpaceship(spaceship);
            Spaceship newSpaceship = spaceship.rotate(rotation);
            objects.set(objects.indexOf(spaceship), newSpaceship);
            owner.setSpaceship(newSpaceship);
        }
    }

    public void shoot(int spaceshipIndex){
        if (spaceshipIndex < objects.size() && objects.get(spaceshipIndex).getType() == ObjectType.SPACESHIP){
            Spaceship spaceship = (Spaceship) objects.get(spaceshipIndex);
            for (GameObject gameObject : objects){
                if (gameObject.getType() == ObjectType.BULLET){
                    Bullet bullet = (Bullet) gameObject;
                    if (!bullet.isVisible() && Objects.equals(bullet.getSpaceshipId(), spaceship.getId())) {
                        Bullet newBullet = bullet.shoot(spaceship);
                        objects.set(objects.indexOf(bullet), newBullet);
                        bullets.set(bullets.indexOf(bullet), newBullet);
                        break;
                    }
                }
            }
        }
    }

    public void showResults(){
        System.out.println("------------------------- RESULTADOS -------------------------");
        for (Player player: players) {
            System.out.println("");
            System.out.println(player.getId() + " obtuvo " + player.getPoints() + " puntos");
            System.out.println("");
        }
        System.out.println("--------------------------------------------------------------");
    }

    public void createPlayers(){
        players = new ArrayList<>();
        int amount = configuration.getAmountOfPlayers();
        for (int i = 1; i <= amount; i++) {
            Spaceship spaceship;
            if (i == 1) {
                spaceship = new Spaceship("spaceship-" + i, configuration.getStyles().get("style-" + i), new ObjectSize(70, 70), new Vector(330, 350), new Vector(0, 1), 0, 180, 20, 100, true, 100, 1000, 1, 0, "player-" + i);
            } else {
                spaceship = new Spaceship("spaceship-" + i, configuration.getStyles().get("style-" + i), new ObjectSize(70, 70), new Vector(410, 350), new Vector(0, 1), 0, 180, 20, 100, true, 100, 1000, 1, 0, "player-" + i);
            }
            players.add(new Player("player-" + i, 0, configuration.getLivesPerPlayer(), spaceship, true));
        }
    }

    public void createBullets(int amount){
        bullets = new ArrayList<>();
        for (int i = 1; i <= amount; i++) {
            Spaceship spaceship = putInSpaceship(amount, configuration.getAmountOfPlayers(), i);
            bullets.add(new Bullet("bullet-" + i, ObjectStyle.BULLET, new ObjectSize(40, 20), new Vector(-100, -100), new Vector(0, 0), 7, 0, 35, false, spaceship.getId()));
            i++;
            bullets.add(new Bullet("bullet-" + i, ObjectStyle.BULLET, new ObjectSize(50, 25), new Vector(-100, -100), new Vector(0, 0), 7, 0, 40, false, spaceship.getId()));
            i++;
            bullets.add(new Bullet("bullet-" + i, ObjectStyle.BULLET, new ObjectSize(60, 30), new Vector(-100, -100), new Vector(0, 0), 7, 0, 45, false, spaceship.getId()));
        }
    }

    private Spaceship putInSpaceship(int amountOfBullets, int amountOfShips, int i){
        List<Spaceship> spaceships = new ArrayList<>();
        for (Player player: players) {
            spaceships.add(player.getSpaceship());
        }
        int aux = amountOfBullets/amountOfShips;
        for (int j = 0; j < amountOfShips; j++) {
            if (i < aux) {
                return spaceships.get(j);
            }
            aux *= 2;
        }
        return spaceships.get(amountOfShips-1);
    }

    public void createAsteroids(){
        asteroids = new ArrayList<>();
        int amount = configuration.getAmountOfAsteroids();
        int amountPerSize = amount / 3;
        for (int i = 1; i <= amountPerSize; i++) {
            asteroids.add(new Asteroid("asteroid-" + i, ObjectStyle.ASTEROID1, new ObjectSize(50, 50), new Vector(-100, -100), new Vector(1, -1), 0, 180, 60, 50, false, 50, 1000));
            i++;
            asteroids.add(new Asteroid("asteroid-" + i, ObjectStyle.ASTEROID1, new ObjectSize(70, 70), new Vector(-100, -100), new Vector(1, -1), 0, 180, 80, 75, false, 75, 2000));
            i++;
            asteroids.add(new Asteroid("asteroid-" + i, ObjectStyle.ASTEROID1, new ObjectSize(90, 90), new Vector(-100, -100), new Vector(1, -1), 0, 180, 100, 100, false, 100, 3000));
        }
    }

    public void makeCollision(String id1, String id2){
        GameObject object1 = null;
        GameObject object2 = null;
        for (GameObject gameObject : objects){
            if (gameObject.getId().equals(id1)) object1 = gameObject;
            if (gameObject.getId().equals(id2)) object2 = gameObject;
        }
        if (object1 != null && object2 != null && object1.isVisible() && object2.isVisible()){
            Collision collision = new Collision(object1, object2, objects, players, this);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isOver() {
        return isOver;
    }

}
