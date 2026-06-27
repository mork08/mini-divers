package my_project.model.modes.planet.entity;

import KAGO_framework.view.DrawTool;
import beckerStructures.BeckerList;

public class EntityManager {

    private static BeckerList<Entity<?>> entities = new BeckerList<>();

    public static void register(Entity<?> entity) {
        EntityManager.entities.append(entity);
    }

    public static void unregister(Entity<?> entity) {
        EntityManager.entities.remove(entity);
    }

    public static void updateAll(double dt) {
        EntityManager.entities.forEach((e, i) -> e.update(dt));
    }

    public static void drawAll(DrawTool drawTool) {
        EntityManager.entities.forEach((e, i) -> e.draw(drawTool));
    }

    public static void keypressedCallback(int key) {
        EntityManager.entities.forEach((e, i) -> e.keypressed(key));
    }

    public static BeckerList<Entity<?>> getEntities() {
        return entities;
    }
    public static BeckerList<Entity<?>> getNearbyEntities(double x, double y, double distance) {
        BeckerList<Entity<?>> returning = new BeckerList<>();
        for (int i = 0; i < entities.getCapacity(); i++) {
            Entity<?> e = entities.get(i);
            if (e != null) {
                double dx = e.getX() - x;
                double dy = e.getY() - y;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if (dist < distance) {
                    returning.append(e);
                }
            }

        }
        return returning;
    }
}
