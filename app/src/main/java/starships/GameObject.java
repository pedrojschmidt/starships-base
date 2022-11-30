package starships;

public class GameObject {
    private final String id;

    private final ObjectType type;
    private final ObjectStyle style;
    private final ObjectShape shape;
    private final ObjectSize size;

    private Vector position;
    private Vector direction;
    private double speed;
    private double rotationDegrees;
    private boolean isVisible;

    private final double damage;
    private final double health;

    public GameObject(String id, ObjectType type, ObjectStyle style, ObjectShape shape, ObjectSize size, Vector position, Vector direction, double speed, double rotationDegrees, double damage, double health, boolean isVisible) {
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
        this.health = health;
        this.isVisible = isVisible;
    }

    public void update() {

    }

    public Double[] getPosRotSz(){
        return new Double[]{position.getX(), position.getY(), rotationDegrees, size.getHeight(), size.getWidth()};
    }

    public void reduceHealth(int amount){
        setHealth(health-amount);
    }

    public boolean isInBounds(){
        return position.getX() > 0 && position.getX() < 800 && position.getY() > 0 && position.getY() < 800;
    }

    public GameObject setId(String id){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
    }

    public GameObject setType(ObjectType type){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
    }

    public GameObject setStyle(ObjectStyle style){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
    }

    public GameObject setShape(ObjectShape shape){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
    }

    public GameObject setSize(ObjectSize size){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
    }

    public GameObject setDamage(double damage){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
    }

    public GameObject setHealth(double health){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, health, isVisible);
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setRotationDegrees(double rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
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

    public double getHealth() {
        return health;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
