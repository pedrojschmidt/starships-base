package starships;

public class Collision {

    private final GameObject object1;
    private final GameObject object2;

    public Collision(GameObject object1, GameObject object2) {
        this.object1 = object1;
        this.object2 = object2;
        handleCollision();
    }

    public void handleCollision(){

    }

    public GameObject getObject1() {
        return object1;
    }

    public GameObject getObject2() {
        return object2;
    }
}
