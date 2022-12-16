package starships;

import starships.gameObjects.Spaceship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsteroidGenerator {

    public static void generateAsteroids(List<GameObject> objects, List<Player> players){
        if (getAsteroidsAmount(objects) < 7) {
            for (GameObject gameObject: objects) {
                if (gameObject.getType().equals(ObjectType.ASTEROID) && !gameObject.isVisible()) {
                    showMeteor(gameObject, getShips(players));
                    break;
                }
            }
        }
    }

    public static void showMeteor(GameObject object, List<Spaceship> spaceships){
        double x;
        double y;
        Vector direction;
        Random random = new Random();
        double aux = random.nextDouble(800);
        int sideOfScreen = random.nextInt(4);
        Spaceship spaceship = spaceships.get(random.nextInt(spaceships.size()));
        switch (sideOfScreen) {
            case 0 -> {
                x = 0;
                y = aux;
                if (spaceship.getPosition().getY() > aux) {
                    direction = new Vector(spaceship.getPosition().getX(), spaceship.getPosition().getY() + aux);
                } else {
                    direction = new Vector(spaceship.getPosition().getX(), spaceship.getPosition().getY() - aux);
                }
            }
            case 1 -> {
                x = aux;
                y = 0;
                if (spaceship.getPosition().getX() > aux) {
                    direction = new Vector(spaceship.getPosition().getX() + aux, spaceship.getPosition().getY());
                } else {
                    direction = new Vector(spaceship.getPosition().getX() - aux, spaceship.getPosition().getY());
                }
            }
            case 2 -> {
                x = 800;
                y = aux;
                if (spaceship.getPosition().getY() > aux) {
                    direction = new Vector(-spaceship.getPosition().getX(), spaceship.getPosition().getY() + aux);
                } else {
                    direction = new Vector(-spaceship.getPosition().getX(), spaceship.getPosition().getY() - aux);
                }
            }
            default -> {
                x = aux;
                y = 800;
                if (spaceship.getPosition().getX() > aux) {
                    direction = new Vector(spaceship.getPosition().getX() + aux, -spaceship.getPosition().getY());
                } else {
                    direction = new Vector(spaceship.getPosition().getX() - aux, -spaceship.getPosition().getY());
                }
            }
        }
        object = object.setPosition(new Vector(x, y));
        object = object.setDirection(direction);
        object = object.setSpeed(0.0015);
        object = object.setVisible(true);
        object = object.setActualHealth(object.getInitialHealth());
    }

    public static List<Spaceship> getShips(List<Player> players){
        List<Spaceship> spaceships = new ArrayList<>();
        for (Player player: players) {
            spaceships.add(player.getSpaceship());
        }
        return spaceships;
    }

    public static int getAsteroidsAmount(List<GameObject> objects){
        int amount = 0;
        for (GameObject gameObject : objects){
            if (gameObject.getType().equals(ObjectType.ASTEROID) && gameObject.isVisible()){
                amount++;
            }
        }
        return amount;
    }
}
