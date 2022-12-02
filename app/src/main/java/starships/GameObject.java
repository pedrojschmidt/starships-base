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
    private double actualHealth;

    private final double damage;
    private final double initialHealth;

    public GameObject(String id, ObjectType type, ObjectStyle style, ObjectShape shape, ObjectSize size, Vector position, Vector direction, double speed, double rotationDegrees, double damage, double initialHealth, boolean isVisible) {
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
        this.actualHealth = initialHealth;
        this.initialHealth = initialHealth;
        this.isVisible = isVisible;
    }

    public void update() {

    }

    public Double[] getPosRotSz(){
        return new Double[]{position.getX(), position.getY(), rotationDegrees, size.getHeight(), size.getWidth()};
    }

    public void reduceHealth(double amount){
        setActualHealth(actualHealth -amount);
    }

    public boolean isInBounds(){
        return position.getX() > 0 && position.getX() < 800 && position.getY() > 0 && position.getY() < 800;
    }

    public void setDirectionFromRotation(double degrees){
        double degreesBetween0and360 = degrees;
        while (degreesBetween0and360 > 360) {
            degreesBetween0and360 = degreesBetween0and360 - 360;
        }
        double radians = Math.toRadians(degreesBetween0and360);
        if (degreesBetween0and360 >= 0 && degreesBetween0and360 <= 90) {
            //De esta manera, el modulo del vector siempre es 1
            double pow = Math.pow(Math.tan(radians), 2);
            double x = Math.sqrt(pow / (1 + pow)); // -x
            double y = x / Math.tan(radians); // +y
            setDirection(new Vector(-x, y));
        } else if (degreesBetween0and360 > 90 && degreesBetween0and360 <= 180) {
            double tan = Math.tan(radians - Math.toRadians(90));
            double pow = Math.pow(tan, 2);
            double y = Math.sqrt(pow / (1 + pow)); // -y
            double x = y / tan; // -x
            setDirection(new Vector(-x, -y));
        } else if (degreesBetween0and360 > 180 && degreesBetween0and360 <= 270) {
            double tan = Math.tan(radians - Math.toRadians(180));
            double pow = Math.pow(tan, 2);
            double x = Math.sqrt(pow / (1 + pow)); // +x
            double y = x / tan; // -y
            setDirection(new Vector(x, -y));
        } else if (degreesBetween0and360 > 270) {
            double tan = Math.tan(radians - Math.toRadians(270));
            double pow = Math.pow(tan, 2);
            double y = Math.sqrt(pow / (1 + pow)); // +y
            double x = y / tan; // +x
            setDirection(new Vector(x, y));
        }
    }

    public GameObject setId(String id){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible);
    }

    public GameObject setType(ObjectType type){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible);
    }

    public GameObject setStyle(ObjectStyle style){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible);
    }

    public GameObject setShape(ObjectShape shape){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible);
    }

    public GameObject setSize(ObjectSize size){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible);
    }

    public GameObject setDamage(double damage){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible);
    }

    public void resetHealth(){

    }

    public void setActualHealth(double actualHealth) {
        this.actualHealth = actualHealth;
    }

    public GameObject setInitialHealth(double initialHealth){
        return new GameObject(id, type, style, shape, size, position, direction, speed, rotationDegrees, damage, initialHealth, isVisible);
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
