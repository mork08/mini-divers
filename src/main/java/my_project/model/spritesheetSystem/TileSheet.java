package my_project.model.spritesheetSystem;

import java.awt.image.BufferedImage;

public class TileSheet extends QuadSheet{
    private int borderSize;
    private int size;
    private int centerSize;
    public TileSheet(BufferedImage texture, int size, int borderSize){
        super(texture);
        this.size = size;
        this.borderSize = borderSize;
        centerSize = size - (borderSize*2);
        generate();
    }
    private void generate(){

        //[0] center
        addQuad(borderSize, borderSize, centerSize, centerSize);

        //- same layer -
        //[1] top-left convex corner
        addQuad(borderSize, borderSize, borderSize, borderSize);
        //[2] top edge
        addQuad(borderSize, 0, centerSize, borderSize);
        //[3] top-right convex corner
        addQuad(centerSize + borderSize, borderSize, borderSize, borderSize);
        //[4] right edge
        addQuad(centerSize + borderSize, borderSize, borderSize, centerSize);
        //[5] bottom-right convex corner
        addQuad(borderSize, borderSize, borderSize, borderSize);
        //[6] bottom edge
        addQuad(borderSize, centerSize + borderSize, centerSize, borderSize);
        //[7] bottom-left convex corner
        addQuad(borderSize, borderSize, borderSize, borderSize);
        //[8] left edge
        addQuad(0, borderSize, borderSize, centerSize);

        //- lower layer -
        //[9] top-left convex corner
        //[10] top edge
        //[11] top-rightconvex corner
        //[12] right edge
        //[13] bottom-rightconvex corner
        //[14] bottom edge
        //[15] bottom-left convex corner
        //[16] left edge

        //- higher layer -
        //[17] top-left convex corner
        //[18] top edge
        //[19] top-rightconvex corner
        //[20] right edge
        //[21] bottom-rightconvex corner
        //[22] bottom edge
        //[23] bottom-left convex corner
        //[24] left edge

        //- concave corners -
        //    > upper layer
        //[25]
        //[26]
        //[27]
        //[28]
        //    > lower layer
        //[29]
        //[30]
        //[31]
        //[32]

    }

    public BufferedImage getCenter(){
        return getSprite(0);
    }

    /**
     *
     * @param direction either "left", "right", "up" or "down"
     * @param heightdifference not currently used
     * @return
     */
    public BufferedImage getEdge(String direction, int heightdifference) {
        switch (direction) {
            case "left":
                return getSprite(8);
            case "right":
                return getSprite(4);
            case "up":
                return getSprite(2);
            case "down":
                return getSprite(6);

        }
        return null;
    }
}
