package starships;

import starships.gameObjects.Bullet;
import starships.gameObjects.Spaceship;

public class GameObject {
    private final String id;

    private final ObjectType type;
    private final ObjectStyle style;
    private final ObjectShape shape;
    private final ObjectSize size;

    private final Vector position;
    private final Vector direction;
    private final double speed;
    private final double rotationDegrees;
    private final boolean isVisible;
    private final double actualHealth;

    private final double damage;
    private final double initialHealth;

    public GameObject(String id, ObjectType type, ObjectStyle style, ObjectShape shape, ObjectSize size, Vector position, Vector direction, double speed, double rotationDegrees, double damage, double initialHealth, boolean isVisible, double actualHealth) {
        this.id = id;
        this.type = type;
        this.style = style;
        this.shape = shape;
        this.size = size;
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.rotationDegrees = rotationDegrees;
        this.damage = damage;
        this.actualHealth = actualHealth;
        this.initialHealth = initialHealth;
        this.isVisible = isVisible;
    }

    public Double[] getPosRotSz(){
        return new Double[]{position.getX(), position.getY(), rotationDegrees, size.getHeight(), size.getWidth()};
    }

    public GameObject reduceHealth(double amount){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth -amount);
    }

    public boolean isInBounds(){
        return position.getX() > 0 && position.getX() < 800 && position.getY() > 0 && position.getY() < 800;
    }

    public GameObject setActualHealth(double actualHealth) {
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);
    }

    public GameObject setPosition(Vector position) {
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);    }

    public GameObject setDirection(Vector direction) {
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);    }

    public GameObject setSpeed(double speed) {
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);    }

    public GameObject setRotationDegrees(double rotationDegrees) {
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);    }

    public GameObject setVisible(boolean visible) {
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, visible, actualHealth);    }

    public GameObject setId(String id){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);
    }

    public GameObject setType(ObjectType type){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);
    }

    public GameObject setStyle(ObjectStyle style){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);
    }

    public GameObject setShape(ObjectShape shape){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);
    }

    public GameObject setSize(ObjectSize size){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);
    }

    public GameObject setDamage(double damage){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);
    }

    public void resetHealth(){

    }

    public GameObject setInitialHealth(double initialHealth){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible, actualHealth);
    }

    public String getId() {
        return id;
    }

    public ObjectType getType() {
        return type;
    }

    public ObjectStyle getStyle() {
        return style;
    }

    public ObjectShape getShape() {
        return shape;
    }

    public ObjectSize getSize() {
        return size;
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    public double getRotationDegrees() {
        return rotationDegrees;
    }

    public double getDamage() {
        return damage;
    }

    public double getActualHealth() {
        return actualHealth;
    }

    public double getInitialHealth() {
        return initialHealth;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
