package my_project.model.spritesheetSystem;

import java.awt.image.BufferedImage;

public class QuadSheet extends SpriteSheet {
    private BufferedImage image;
    public QuadSheet() {
        super();
    }
    public QuadSheet(BufferedImage image) {
        super();
        this.image = image;
    }
    public void addQuad(int x, int y, int width, int height){
        addSprite(image.getSubimage(x, y, width, height));
    }
    public BufferedImage getImage(){
        return image;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }

}
