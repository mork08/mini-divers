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
        addQuad(0, 0, borderSize, borderSize);
        //[2] top edge
        addQuad(borderSize, 0, centerSize, borderSize);
        //[3] top-right convex corner
        addQuad(centerSize + borderSize, 0, borderSize, borderSize);
        //[4] right edge
        addQuad(centerSize + borderSize, borderSize, borderSize, centerSize);
        //[5] bottom-right convex corner
        addQuad(centerSize + borderSize, centerSize + borderSize, borderSize, borderSize);
        //[6] bottom edge
        addQuad(borderSize, centerSize + borderSize, centerSize, borderSize);
        //[7] bottom-left convex corner
        addQuad(0, centerSize + borderSize, borderSize, borderSize);
        //[8] left edge
        addQuad(0, borderSize, borderSize, centerSize);

        //- lower layer -
        //[9] top-left convex corner
        addQuad(size, 0, borderSize, borderSize);
        //[10] top edge
        addQuad(size + borderSize, 0, centerSize, borderSize);
        //[11] top-right convex corner
        addQuad(size + centerSize + borderSize, 0, borderSize, borderSize);
        //[12] right edge
        addQuad(size + centerSize + borderSize, borderSize, borderSize, centerSize);
        //[13] bottom-right convex corner
        addQuad(size + centerSize +  borderSize, centerSize + borderSize, borderSize, borderSize);
        //[14] bottom edge
        addQuad(size + borderSize, centerSize + borderSize, centerSize, borderSize);
        //[15] bottom-left convex corner
        addQuad(size + 0, centerSize + borderSize, borderSize, borderSize);
        //[16] left edge
        addQuad(size + 0, borderSize, borderSize, centerSize);

        //- higher layer -
        //[17] top-left convex corner
        addQuad(size * 2, 0, borderSize, borderSize);
        //[18] top edge
        addQuad(size * 2 + borderSize, 0, centerSize, borderSize);
        //[19] top-right convex corner
        addQuad(size * 2 + centerSize + borderSize, 0, borderSize, borderSize);
        //[20] right edge
        addQuad(size * 2 + centerSize + borderSize, borderSize, borderSize, centerSize);
        //[21] bottom-right convex corner
        addQuad(size * 2 + centerSize + borderSize, centerSize + borderSize, borderSize, borderSize);
        //[22] bottom edge
        addQuad(size * 2 + borderSize, centerSize + borderSize, centerSize, borderSize);
        //[23] bottom-left convex corner
        addQuad(size * 2 + 0, centerSize + borderSize, borderSize, borderSize);
        //[24] left edge
        addQuad(size * 2 + 0, borderSize, borderSize, centerSize);

        //- concave corners -
        //    > lower layer
        //[25] top-left concave corner
        addQuad(size * 3 + 0, 0, borderSize, borderSize);
        //[26] top-right concave corner
        addQuad(size * 3 + borderSize, 0, borderSize, borderSize);
        //[27] bottom-right concave corner
        addQuad(size * 3 + borderSize, borderSize, borderSize, borderSize);
        //[28] bottom-left concave corner
        addQuad(size * 3 + 0, borderSize, borderSize, borderSize);

        //    > upper layer
        //[29] top-left concave corner
        addQuad(size * 3 + 0 + borderSize * 2, 0, borderSize, borderSize);
        //[30] top-right concave corner
        addQuad(size * 3 + borderSize + borderSize * 2, 0, borderSize, borderSize);
        //[31] bottom-right concave corner
        addQuad(size * 3 + borderSize + borderSize * 2, borderSize, borderSize, borderSize);
        //[32] bottom-left concave corner
        addQuad(size * 3 + borderSize * 2, borderSize, borderSize, borderSize);
        ////ONLY WORKS WITH centerSize = 2 * borderSize PROPERLY! OTHER PROPORTIONS MAY AND WILL LEAD TO VISUAL ERRORS
        // - straight corners down-
        //    > horizontal
        //[33] upper left
        addQuad(size * 1 + borderSize * 2, 0, borderSize, borderSize);
        //[34] upper right
        addQuad(size * 1 + borderSize * 1, 0, borderSize, borderSize);
        //[35] lower right
        addQuad(size * 1 + borderSize * 1, centerSize + borderSize, borderSize, borderSize);
        //[36] lower left
        addQuad(size * 1 + borderSize * 2, centerSize + borderSize, borderSize, borderSize);
        //    > vertical
        //[37] upper left
        addQuad(size * 1, borderSize * 2, borderSize, borderSize);
        //[38] upper right
        addQuad(size * 1 + centerSize + borderSize, borderSize * 2, borderSize, borderSize);
        //[39] lower right
        addQuad(size * 1 + centerSize + borderSize, borderSize * 1, borderSize, borderSize);
        //[40] lower left
        addQuad(size * 1, borderSize * 1, borderSize, borderSize);
        // - straight corners up-
        //    > vertical
        //[41]
        addQuad(size * 2 + borderSize * 2, 0, borderSize, borderSize);
        //[42] upper right
        addQuad(size * 2 + borderSize * 1, 0, borderSize, borderSize);
        //[43] lower right
        addQuad(size * 2 + borderSize * 1, centerSize + borderSize, borderSize, borderSize);
        //[44] lower left
        addQuad(size * 2 + borderSize * 2, centerSize + borderSize, borderSize, borderSize);
        //    > vertical
        //[45] upper left
        addQuad(size * 2, borderSize * 2, borderSize, borderSize);
        //[46] upper right
        addQuad(size * 2 + centerSize + borderSize, borderSize * 2, borderSize, borderSize);
        //[47] lower right
        addQuad(size * 2 + centerSize + borderSize, borderSize * 1, borderSize, borderSize);
        //[48] lower left
        addQuad(size * 2, borderSize * 1, borderSize, borderSize);

        System.out.println("generated tile sheet, starting debug check for nullpointers");
        boolean nullpointer = false;
        for (int i = 0; i < 49; i++) {
            if (getSprite(i) == null) {
                System.out.println("Tile sheet " + i + " is null!");
                nullpointer = true;
            }else System.out.println("Tile sheet " + i + " is not null :)");
        }
        //if (nullpointer) {System.exit(0);}
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

    public BufferedImage getCorner(String Direction, int heightDifference1, int heightDifference2, int diagonalHeightDifference) {
        int PLACEHOLDER = 1;
        boolean sameHeight = heightDifference1 == 0 && heightDifference2 == 0;
        if (sameHeight) {
            if (diagonalHeightDifference == 0) {
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
            } else if (diagonalHeightDifference < 0) {
                switch (Direction) {
                    case "topLeft":
                        return getSprite(25);
                    case "topRight":
                        return getSprite(26);
                    case "downRight":
                        return getSprite(27);
                    case "downLeft":
                        return getSprite(28);

                }
            } else {
                switch (Direction) {
                    case "topLeft":
                        return getSprite(29);
                    case "topRight":
                        return getSprite(30);
                    case "downRight":
                        return getSprite(31);
                    case "downLeft":
                        return getSprite(32);

                }
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

        } else if (heightDifference1 < 0 && heightDifference2 < 0) {
            switch (Direction) {
                case "topLeft":
                    return getSprite(9);
                case "topRight":
                    return getSprite(11);
                case "downRight":
                    return getSprite(13);
                case "downLeft":
                    return getSprite(15);
            }
        } else if (heightDifference1 == 0 && heightDifference2 != 0) {
            if (heightDifference2 < 0) {
                switch (Direction) {
                    case "topLeft":
                        return getSprite(33);
                    case "topRight":
                        return getSprite(38);
                    case "downRight":
                        return getSprite(35);
                    case "downLeft":
                        return getSprite(40);
                }
            }else{
                switch (Direction) {
                    case "topLeft":
                        return getSprite(33+8);
                    case "topRight":
                        return getSprite(38+8);
                    case "downRight":
                        return getSprite(35+8);
                    case "downLeft":
                        return getSprite(40+8);
                }
            }
        }else if (heightDifference1 != 0 && heightDifference2 == 0) {
            if (heightDifference1 < 0) {
                switch (Direction) {
                    case "topLeft":
                        return getSprite(37);
                    case "topRight":
                        return getSprite(34);
                    case "downRight":
                        return getSprite(39);
                    case "downLeft":
                        return getSprite(36);
                }
            }else{
                switch (Direction) {
                    case "topLeft":
                        return getSprite(37+8);
                    case "topRight":
                        return getSprite(34+8);
                    case "downRight":
                        return getSprite(39+8);
                    case "downLeft":
                        return getSprite(36+8);
                }
            }
        }
        return getSprite(1);
    }

}
