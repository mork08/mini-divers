package my_project.model.spritesheetSystem;

import java.awt.image.BufferedImage;

public class TileSheet extends QuadSheet{
    private int borderSize;
    private int size;
    public TileSheet(BufferedImage texture, int size, int borderSize){

    }
    private void generate(){
        /*
            //[1] center

            //- same layer -
            //[2] top-left convex corner
            //[3] top edge
            //[4] top-rightconvex corner
            //[5] right edge
            //[6] bottom-rightconvex corner
            //[7] bottom edge
            //[8] bottom-left convex corner
            //[9] left edge

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
}
