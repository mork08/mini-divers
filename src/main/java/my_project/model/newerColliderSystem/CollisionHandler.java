package my_project.model.newerColliderSystem;


import my_project.model.modes.planet.Operation;
import my_project.model.modes.planet.Tilesystem.Tile;

public class CollisionHandler {

    private static Operation operation;

    public static void setOperation(Operation o) {
        operation = o;
    }

    public static boolean collidesWithTile(Collider collider) {
        collider.getCage().updatePosition();
        if (operation == null) return false;
        int widthInTiles = (int)(collider.getWidth()/ Tile.TILE_SIZE) + 1;
        int heightInTiles = (int)(collider.getHeight()/Tile.TILE_SIZE) + 1;
        for (int x = 0; x <= widthInTiles; x++) {
            for (int y = 0; y <= heightInTiles; y++) {
                Tile tile = operation.getTileMap().getTileByPosition(collider.getX() + x * Tile.TILE_SIZE, collider.getY() + y * Tile.TILE_SIZE);
                boolean collides = collider.collidesWith(tile);
                boolean isSolid = tile != null ? (tile.getLevelHeight() != 0) : false;
                if (collides && isSolid) {
                    collider.setCollides(true);
                    return true;
                } else if (collides && !isSolid) {
                }
            }
        }
        return false;
    }
}
