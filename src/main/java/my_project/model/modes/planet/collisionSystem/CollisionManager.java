package my_project.model.modes.planet.collisionSystem;

import beckerStructures.BeckerList;

public class CollisionManager {
    BeckerList<Collider> colliders;

    public CollisionManager() {
        this.colliders = new BeckerList<>();
    }

    public void update(double dt){
        while(colliders.hasAccess()){
            colliders.getContent().calculateLinVel(dt);
        }
        for (int i = 0; i < colliders.getLength(); i++) {
            Collider curr = colliders.get(i);
            for (int j = 0; j < colliders.getLength(); j++) {
                if (j != i){//wenn beide collider sie selben sind, sollen sie nicht überprüft werden
                    if (curr.collidesWith(colliders.get(j))) {
                        //bewegt die beiden Collider zurück, bis sie nicht mehr überlappen
                        while (curr.collidesWith(colliders.get(j))) {
                            curr.stepBack();
                            colliders.get(j).stepBack();
                        }
                    }
                }
            }
        }
    }
    public void addCollider(Collider collider){
        colliders.append(collider);
    }
    public void removeCollider(Collider collider){
        for (int i = 0; i < colliders.getLength(); i++) {
            if(colliders.get(i) == collider){
                colliders.set(i, null);
            }
        }
    }
    public void clearColliders() {
        colliders = new BeckerList<>();
    }
}
