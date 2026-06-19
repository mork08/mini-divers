package my_project.model.modes.planet.collisionSystem;

import beckerStructures.BeckerList;

public class CollisionManager {
    BeckerList<Collider> colliders;

    public void update(double dt){
        while(colliders.hasAccess()){
            colliders.getContent().calculateLinVel(dt);
        }
        for (int i = 0; i < colliders.getLength(); i++) {
            Collider curr = colliders.get(i);
            for (int j = 0; j < colliders.getLength(); j++) {
                if (j != i){
                    // TODO Kollisionen checken und handeln
                }
            }
        }
    }
}
