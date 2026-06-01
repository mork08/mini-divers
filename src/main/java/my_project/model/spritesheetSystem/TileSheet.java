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

        //[1] center
        addQuad(borderSize, borderSize, centerSize, centerSize);
/*
        //- same layer -
        //[2] top-left convex corner
        addQuad(borderSize, borderSize, borderSize, borderSize);
        //[3] top edge
        addQuad(borderSize, 0, centerSize, borderSize);
        //[4] top-right convex corner
        addQuad(centerSize + borderSize, borderSize, borderSize, borderSize);
        //[5] right edge
        addQuad(centerSize + borderSize, borderSize, borderSize, centerSize);
        //[6] bottom-right convex corner
        addQuad(borderSize, borderSize, borderSize, borderSize);
        //[7] bottom edge
        addQuad(borderSize, centerSize + borderSize, centerSize, borderSize);
        //[8] bottom-left convex corner
        addQuad(borderSize, borderSize, borderSize, borderSize);
        //[9] left edge
        addQuad(0, borderSize, borderSize, centerSize);

        //- lower layer -
        //[10] top-left convex corner
        //[11] top edge
        //[12] top-rightconvex corner
        //[13] right edge
        //[14] bottom-rightconvex corner
        //[15] bottom edge
        //[16] bottom-left convex corner
        //[17] left edge

        //- higher layer -
        //[10] top-left convex corner
        //[11] top edge
        //[12] top-rightconvex corner
        //[13] right edge
        //[14] bottom-rightconvex corner
        //[15] bottom edge
        //[16] bottom-left convex corner
        //[17] left edge

        //- concave corners -
        //    > upper layer
        //[18]
        //[19]
        //[20]
        //[21]
        //    > lower layer
        //[22]
        //[23]
        //[24]
        //[25]

 */
    }

    public BufferedImage getCenter(){
        return getSprite(0);
    }
}
