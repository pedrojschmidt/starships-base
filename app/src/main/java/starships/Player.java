package starships;

import starships.gameObjects.Spaceship;

public class Player {

    private final String id;
    private int points;
    private int lives;
    private Spaceship spaceship;
    private boolean isAlive;

    public Player(String id, int points, int lives, Spaceship spaceship, boolean isAlive) {
        this.id = id;
        this.points = points;
        this.lives = lives;
        this.spaceship = spaceship;
        this.isAlive = isAlive;
    }

    public void removeLife(){
        lives -= 1;
    }

    public void addPoints(int points){
        this.points += points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public int getLives() {
        return lives;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
