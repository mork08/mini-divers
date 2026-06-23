package my_project.model.spritesheetSystem;

import java.awt.image.BufferedImage;

public class PlanetSheet {
    private int heights;
    private TileSheet[] tileSheets;
    private BufferedImage mainImage;
    private int borderSize;
    public PlanetSheet(int heights, BufferedImage mainImage, int borderSize) {
        this.heights = heights;
        this.mainImage = mainImage;
        this.borderSize = borderSize;
        tileSheets = new TileSheet[heights];
        for (int i = 0; i < heights; i++) {
            System.out.println("Planet sheet subsheet " + i + " is created");
            tileSheets[i] = new TileSheet(mainImage.getSubimage(0, (mainImage.getHeight()/heights)*i, mainImage.getWidth(), mainImage.getHeight()/heights), mainImage.getHeight()/heights, borderSize);
        }
    }
    public TileSheet getTileSheet(int height){
        return tileSheets[height];
    }
}
