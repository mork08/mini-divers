package my_project.model.modes.planet.collisionSystem;

import beckerStructures.BeckerList;

public class CollisionManager {
    static BeckerList<Collider> colliders = new BeckerList<>();;

    public static void update(double dt){
        colliders.forEach((collider, index) -> {
            colliders.getContent().calculateLinVel(dt);

            Collider curr = colliders.get(index);
            for (int j = 0; j < colliders.getLength(); j++) {
                if (j != index) {//wenn beide collider sie selben sind, sollen sie nicht überprüft werden
                    if (curr.collidesWith(colliders.get(j))) {
                        //bewegt die beiden Collider zurück, bis sie nicht mehr überlappen
                        //while (curr.collidesWith(colliders.get(j))) {
                            //curr.stepBack();
                            //colliders.get(j).stepBack();
                        //}
                    }
                }
            }
        });
    }

    public static void addCollider(Collider collider){
        colliders.append(collider);
    }

    public static void removeCollider(Collider collider){
        for (int i = 0; i < colliders.getLength(); i++) {
            if(colliders.get(i) == collider){
                colliders.set(i, null);
            }
        }
    }

    public static void clearColliders() {
        colliders = new BeckerList<>();
    }
}
