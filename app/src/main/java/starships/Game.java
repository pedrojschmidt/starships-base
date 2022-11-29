package starships;

import starships.configuration.Configuration;
import starships.gameObjects.Asteroid;
import starships.gameObjects.Bullet;
import starships.gameObjects.Spaceship;

import java.util.List;

public class Game {

    private List<Player> players;
    private List<Asteroid> asteroids;
    private List<Bullet> bullets;
    private List<GameObject> objects;

    private Configuration configuration;

    private boolean isPaused;

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

    public void createPlayers(){
        int amount = configuration.getAmountOfPlayers();
        for (int i = 0; i < amount; i++) {
            Spaceship spaceship = new Spaceship("spaceship-" + i, configuration.getStyles().get("style-" + i), new ObjectSize(20, 20), new Vector(300, 300), new Vector(0, 1), 0, 180, 20, 100, true, 1000, 1, 0);
            players.add(new Player("player-" + i, 0, configuration.getLivesPerPlayer(), spaceship, true));
        }
    }

    public void createAsteroids(){
        int amount = configuration.getAmountOfAsteroids();
        for (int i = 0; i < amount; i++) {
            asteroids.add(new Asteroid("asteroid-" + i, ObjectStyle.ASTEROID1, new ObjectSize(30, 30), new Vector(0, 800), new Vector(1, -1), 0, 180, 20, 100, true, 1000));
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
}
