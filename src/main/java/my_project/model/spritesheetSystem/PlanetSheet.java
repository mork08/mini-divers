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
            tileSheets[i] = new TileSheet(mainImage.getSubimage(0, 32, mainImage.getWidth(), 32), 32, borderSize);

        }
    }
    public TileSheet getTileSheet(int height){
        return tileSheets[height];
    }
}
