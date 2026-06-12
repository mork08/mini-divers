package my_project.model.spritesheetSystem;

import java.awt.image.BufferedImage;

public class TileSheet extends QuadSheet {
    private int borderSize;
    private int size;
    private int centerSize;

    public TileSheet(BufferedImage texture, int size, int borderSize) {
        super(texture);
        this.size = size;
        this.borderSize = borderSize;
        centerSize = size - (borderSize * 2);
        generate();
    }

    private void generate() {

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
        addQuad(size + borderSize, borderSize, borderSize, borderSize);
        //[10] top edge
        addQuad(size + borderSize, 0, centerSize, borderSize);
        //[11] top-right convex corner
        addQuad(size + centerSize + borderSize, borderSize, borderSize, borderSize);
        //[12] right edge
        addQuad(size + centerSize + borderSize, borderSize, borderSize, centerSize);
        //[13] bottom-rightconvex corner
        addQuad(size + borderSize, borderSize, borderSize, borderSize);
        //[14] bottom edge
        addQuad(size + borderSize, centerSize + borderSize, centerSize, borderSize);
        //[15] bottom-left convex corner
        addQuad(size + borderSize, borderSize, borderSize, borderSize);
        //[16] left edge
        addQuad(size + 0, borderSize, borderSize, centerSize);

        //- higher layer -
        //[17] top-left convex corner
        addQuad(size * 2 + borderSize, borderSize, borderSize, borderSize);
        //[18] top edge
        addQuad(size * 2 + borderSize, 0, centerSize, borderSize);
        //[19] top-right convex corner
        addQuad(size * 2 + centerSize + borderSize, borderSize, borderSize, borderSize);
        //[20] right edge
        addQuad(size * 2 + centerSize + borderSize, borderSize, borderSize, centerSize);
        //[21] bottom-rightconvex corner
        addQuad(size * 2 + borderSize, borderSize, borderSize, borderSize);
        //[22] bottom edge
        addQuad(size * 2 + borderSize, centerSize + borderSize, centerSize, borderSize);
        //[23] bottom-left convex corner
        addQuad(size * 2 + borderSize, borderSize, borderSize, borderSize);
        //[24] left edge
        addQuad(size * 2 + 0, borderSize, borderSize, centerSize);

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

    public BufferedImage getCenter() {
        return getSprite(0);
    }

    /**
     *
     * @param direction        either "left", "right", "up" or "down"
     * @param heightDifference not currently used
     * @return
     */
    public BufferedImage getEdge(String direction, int heightDifference) {
        //heightDifference = 0;
        if (heightDifference == 0) {
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
        } else if (heightDifference < 0) {
            switch (direction) {
                case "left":
                    return getSprite(16);
                case "right":
                    return getSprite(12);
                case "up":
                    return getSprite(10);
                case "down":
                    return getSprite(14);
            }
        } else {
            switch (direction) {
                case "left":
                    return getSprite(24);
                case "right":
                    return getSprite(20);
                case "up":
                    return getSprite(18);
                case "down":
                    return getSprite(22);
            }
        }
        return null;
    }

    public BufferedImage getCorner(String Direction, int heightDifference1, int heightDifference2) {
        boolean sameHeight = heightDifference1 == 0 && heightDifference2 == 0;
        if (sameHeight) {
            switch (Direction) {
                case "topLeft":
                    return getSprite(1);
                case "topRight":
                    return getSprite(3);
                case "downRight":
                    return getSprite(5);
                case "downLeft":
                    return getSprite(7);

            }
        } else if (heightDifference1 > 0 && heightDifference2 > 0) {
            switch (Direction) {
                case "topLeft":
                    return getSprite(17);
                case "topRight":
                    return getSprite(19);
                case "downRight":
                    return getSprite(21);
                case "downLeft":
                    return getSprite(23);
            }

        }
        return getSprite(1);
    }

}
