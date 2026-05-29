package my_project.model.spritesheetSystem;

import KAGO_framework.view.DrawTool;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {
    private ArrayList<BufferedImage> sprites;

    public SpriteSheet() {
        sprites = new ArrayList<>();
    }

    public void addSprite(BufferedImage image) {
        sprites.add(image);
    }
    public void addSprite(String path) {
        sprites.add(DrawTool.getNewImage(path));
    }
    public BufferedImage getSprite(int index) {
        return sprites.get(index);
    }
    public void setSprite(BufferedImage image, int index) {
        sprites.set(index, image);
    }
    public void setSprite(String path, int index) {
        setSprite(DrawTool.getNewImage(path), index);
    }

    public void setImage(BufferedImage image){
        image = image;
    }
    public void setImage(String path){
        setImage(DrawTool.getNewImage(path));
    }
}
