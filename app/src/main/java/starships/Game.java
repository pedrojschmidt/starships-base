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

    public void moveShip(int spaceshipIndex, boolean accelerate){
        if (spaceshipIndex < objects.size() && objects.get(spaceshipIndex).getType() == ObjectType.SPACESHIP && !isPaused){
            Spaceship spaceship = (Spaceship) objects.get(spaceshipIndex);
            spaceship.move(accelerate);
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
            System.out.println("Jugador: " + player.getId() + " obtuvo " + player.getPoints() + " puntos");
            System.out.println("");
        }
        System.out.println("--------------------------------------------------------------");
    }

    public void createPlayers(){
        players = new ArrayList<>();
        int amount = configuration.getAmountOfPlayers();
        for (int i = 1; i <= amount; i++) {
            Spaceship spaceship = new Spaceship("spaceship-" + i, configuration.getStyles().get("style-" + i), new ObjectSize(70, 70), new Vector(250 + i * 80, 350), new Vector(0, 1), 0, 180, 20, 100, true, 1000, 1, 0, "player-" + i);
            players.add(new Player("player-" + i, 0, configuration.getLivesPerPlayer(), spaceship, true));
        }
    }

    public void createBullets(int amount){
        bullets = new ArrayList<>();
//        for (int i = 1; i <= amount; i++) {
//            String id = "bullet-" + i;
//            double[] pos = NonVisibleObjectsManager.getEmptyPlace();
//            Spaceship spaceship = putInSpaceship(amount, configuration.getAmountOfPlayers(), i);
//            bullets.add(new Bullet(id, pos[0], pos[1], 180, 15, 5, spaceship, 180));
//        }
    }

    private Spaceship putInSpaceship(int amountOfBullets, int amountOfShips, int i){
        int aux = amountOfBullets/amountOfShips;
        for (int j = 0; j < amountOfShips; j++) {
            if (i < aux) return (Spaceship) objects.get(j);
            aux *= 2;
        }
        return (Spaceship) objects.get(amountOfShips-1);
    }

    public void createAsteroids(){
        asteroids = new ArrayList<>();
        int amount = configuration.getAmountOfAsteroids();
        for (int i = 1; i <= amount; i++) {
            asteroids.add(new Asteroid("asteroid-" + i, ObjectStyle.ASTEROID1, new ObjectSize(70, 70), new Vector(0, 0), new Vector(1, -1), 0, 180, 20, 100, true, 1000));
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
