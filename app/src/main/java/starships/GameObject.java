package starships;

public class GameObject {
    private final String id;

    private final ObjectType type;
    private final ObjectStyle style;
    private final ObjectShape shape;
    private final ObjectSize size;

    private final Vector position;
    private final Vector direction;
    private final Vector speed;
    private final int rotationDegrees;

    private final int damage;
    private final boolean isVisible;

    public GameObject(String id, ObjectType type, ObjectStyle style, ObjectShape shape, ObjectSize size, Vector position, Vector direction, Vector speed, int rotationDegrees, int damage, boolean isVisible) {
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
        this.isVisible = isVisible;
    }

    public boolean isInBounds(){
        return position.getX() > 0 && position.getX() < 800 && position.getY() > 0 && position.getY() < 800;
    }

    public GameObject setId(String id){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, isVisible);
    }

    public GameObject setType(ObjectType type){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, isVisible);
    }

    public GameObject setStyle(ObjectStyle style){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, isVisible);
    }

    public GameObject setShape(ObjectShape shape){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, isVisible);
    }

    public GameObject setSize(ObjectSize size){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, isVisible);
    }

    public GameObject setPosition(Vector position){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, isVisible);
    }

    public GameObject setDirection(Vector direction){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, isVisible);
    }

    public GameObject setSpeed(Vector speed){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, isVisible);
    }

    public GameObject setRotation(int rotationDegrees){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, isVisible);
    }

    public GameObject setDamage(int damage){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, isVisible);
    }

    public GameObject setIsInBounds(boolean isInBounds){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, isInBounds);
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

    public Vector getSpeed() {
        return speed;
    }

    public int getRotationDegrees() {
        return rotationDegrees;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
