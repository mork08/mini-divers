package my_project.model.modes.planet.Tilesystem;

import KAGO_framework.view.DrawTool;
import beckerStructures.BeckerList;

import java.awt.image.BufferedImage;

public class SurfaceMapContainer {
    static BeckerList<BufferedImage> mapCollection = new BeckerList<>();
    public SurfaceMapContainer() {
        addMap("Map 1");
    }
    public static BufferedImage getRandomMap() {
        return getMap((int)(Math.random()* mapCollection.getLength()));
    }
    public static BufferedImage getMap(int i) {
        return mapCollection.get(i);
    }
    private void addMap(String mapName) {
        String path = "src/main/resources/graphic/map files/" + mapName + ".png";
        BufferedImage image = DrawTool.getNewImage(path);
        mapCollection.append(image);
        System.out.println(path + " is " + (image == null ? "null" : "not null"));
    }
}
