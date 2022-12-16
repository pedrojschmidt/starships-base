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
                AsteroidGenerator.generateAsteroids(objects, players);
                List<GameObject> objectsAux = new ArrayList<>();
                objectsAux.addAll(objects);
                for (GameObject gameObject : objects){
                    if (gameObject.getType() == ObjectType.SPACESHIP) {
                        Spaceship spaceship = (Spaceship) gameObject;
                        Spaceship newSpaceship = spaceship.update();

                        objectsAux.remove(spaceship);
                        objectsAux.add(newSpaceship);

                        Player owner = getPlayerBySpaceship(spaceship);
                        owner.setSpaceship(newSpaceship);
                    } else if (gameObject.getType() == ObjectType.BULLET){
                        Bullet bullet = (Bullet) gameObject;

                        objectsAux.remove(bullet);
                        objectsAux.add(bullet.update());

                        List<Bullet> bulletsAux = new ArrayList<>();
                        bulletsAux.addAll(bullets);
                        bulletsAux.remove(bullet);
                        bulletsAux.add(bullet);
                    } else if (gameObject.getType() == ObjectType.ASTEROID){
                        Asteroid asteroid = (Asteroid) gameObject;

                        objectsAux.remove(asteroid);
                        objectsAux.add(asteroid.update());

                        List<Asteroid> asteroidsAux = new ArrayList<>();
                        asteroidsAux.addAll(asteroids);
                        asteroidsAux.remove(asteroid);
                        asteroidsAux.add(asteroid);
                    }
                }
                objects = objectsAux;
            }
        }
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
            spaceship.move(direction);
        }
    }
    public void rotateShip(int spaceshipIndex, double rotation){
        if (spaceshipIndex < objects.size() && objects.get(spaceshipIndex).getType() == ObjectType.SPACESHIP && !isPaused){
            Spaceship spaceship = (Spaceship) objects.get(spaceshipIndex);
            spaceship.rotate(rotation);
        }
    }

    public void shoot(int spaceshipIndex){
        if (spaceshipIndex < objects.size() && objects.get(spaceshipIndex).getType() == ObjectType.SPACESHIP){
            Spaceship spaceship = (Spaceship) objects.get(spaceshipIndex);
            for (GameObject gameObject : objects){
                if (gameObject.getType() == ObjectType.BULLET){
                    Bullet bullet = (Bullet) gameObject;
                    if (!bullet.isVisible() && Objects.equals(bullet.getSpaceshipId(), spaceship.getId())) {
                        bullet.shoot(spaceship);
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
                spaceship = new Spaceship("spaceship-" + i, configuration.getStyles().get("style-" + i), new ObjectSize(70, 70), new Vector(330, 350), new Vector(0, 1), 0, 180, 20, 100, true, 1000, 1, 0, "player-" + i);
            } else {
                spaceship = new Spaceship("spaceship-" + i, configuration.getStyles().get("style-" + i), new ObjectSize(70, 70), new Vector(410, 350), new Vector(0, 1), 0, 180, 20, 100, true, 1000, 1, 0, "player-" + i);
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
            asteroids.add(new Asteroid("asteroid-" + i, ObjectStyle.ASTEROID1, new ObjectSize(50, 50), new Vector(-100, -100), new Vector(1, -1), 0, 180, 20, 50, false, 1000));
            i++;
            asteroids.add(new Asteroid("asteroid-" + i, ObjectStyle.ASTEROID1, new ObjectSize(70, 70), new Vector(-100, -100), new Vector(1, -1), 0, 180, 40, 75, false, 2000));
            i++;
            asteroids.add(new Asteroid("asteroid-" + i, ObjectStyle.ASTEROID1, new ObjectSize(90, 90), new Vector(-100, -100), new Vector(1, -1), 0, 180, 60, 100, false, 3000));
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
            Collision collision = new Collision(object1, object2, objects, players);
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
