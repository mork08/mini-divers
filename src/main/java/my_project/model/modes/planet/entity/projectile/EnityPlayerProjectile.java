package my_project.model.modes.planet.entity.projectile;

import my_project.model.modes.planet.entity.Entity;

import java.awt.*;

public class EnityPlayerProjectile extends EntityProjectile {

    public EnityPlayerProjectile(double startX, double startY, double targetX, double targetY, Entity<?> target, double damage, Color color) {
        super(startX, startY, targetX, targetY, target, damage, color);
    }
}
