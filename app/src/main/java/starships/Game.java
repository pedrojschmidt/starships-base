package starships;

import javafx.scene.input.KeyCode;
import starships.configuration.Configuration;
import starships.gameObjects.Asteroid;
import starships.gameObjects.Bullet;
import starships.gameObjects.Spaceship;

import java.util.List;
import java.util.Map;
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
        createAsteroids();
        fillObjects();
    }

    private void fillObjects(){
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

    public void reset(){
        newGame();
    }

    public void update(){
        if (!isPaused){
            AsteroidGenerator.generateAsteroids(objects, players);
            for (GameObject gameObject : objects){
                gameObject.update();
            }
        }
    }

    public void moveShip(int shipNum, boolean up){
        if (shipNum < objects.size() && objects.get(shipNum).getType() == ObjectType.SPACESHIP && !isPaused){
            Spaceship spaceship = (Spaceship) objects.get(shipNum);
            spaceship.move(up);
        }
    }
    public void rotateShip(int shipNum, double rotation){
        if (shipNum < objects.size() && objects.get(shipNum).getType() == ObjectType.SPACESHIP && !isPaused){
            Spaceship spaceship = (Spaceship) objects.get(shipNum);
            spaceship.rotate(rotation);
        }
    }

    public void shoot(int ship){
        if (ship < objects.size() && objects.get(ship).getType() == ObjectType.SPACESHIP){
            for (GameObject gameObject : objects){
                Spaceship bulletsShip = (Spaceship) objects.get(ship);
                if (gameObject.getType() == ObjectType.BULLET){
                    Bullet bullet = (Bullet) gameObject;
                    if (!bullet.isVisible() && Objects.equals(bullet.getSpaceshipId(), objects.get(ship).getId())) {
                        bullet.shoot();
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
            System.out.println("Jugador: " + player.getId() + " obtuvo " + player.getPoints() + " puntos");
            System.out.println("");
        }
        System.out.println("--------------------------------------------------------------");
    }

    public void createPlayers(){
        int amount = configuration.getAmountOfPlayers();
        for (int i = 0; i < amount; i++) {
            Spaceship spaceship = new Spaceship("spaceship-" + i, configuration.getStyles().get("style-" + i), new ObjectSize(20, 20), new Vector(300, 300), new Vector(0, 1), 0, 180, 20, 100, true, 1000, 1, 0, playerId);
            players.add(new Player("player-" + i, 0, configuration.getLivesPerPlayer(), spaceship, true));
        }
    }

    public void createAsteroids(){
        int amount = configuration.getAmountOfAsteroids();
        for (int i = 0; i < amount; i++) {
            asteroids.add(new Asteroid("asteroid-" + i, ObjectStyle.ASTEROID1, new ObjectSize(30, 30), new Vector(0, 800), new Vector(1, -1), 0, 180, 20, 100, true, 1000));
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
            Collision collision = new Collision(object1, object2, objects, players, objects1, players1);
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
