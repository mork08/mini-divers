package my_project.model.modes.planet.entity.projectile;

import beckerStructures.BeckerList;
import my_project.model.modes.planet.entity.Entity;
import my_project.model.modes.planet.entity.EntityManager;
import my_project.model.modes.planet.entity.EntityPlayer;

import java.awt.*;

public class EntityPlayerProjectile extends EntityProjectile {

    public EntityPlayerProjectile(double startX, double startY, double targetX, double targetY, double damage, Color color) {
        super(startX, startY, targetX, targetY, null, damage, color);
    }
    @Override
    public void update(double dt) {
        super.update(dt);
        double distance = 16;
        BeckerList<Entity<?>> entities = EntityManager.getEntities();
        for (int i = 0; i < entities.getCapacity(); i++) {
            Entity<?> e = entities.get(i);
            if (e != null && !(e instanceof EntityPlayer) && !(e instanceof EntityPlayerProjectile)) {
                double dx = e.getX() - x;
                double dy = e.getY() - y;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if (dist < distance) {
                    e.damage(damage);
                    this.destroy();
                    break;
                }
            }

        }
    }
}
