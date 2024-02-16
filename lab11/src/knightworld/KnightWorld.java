package knightworld;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * Draws a world consisting of knight-move holes.
 * For center (x, y), only consider (x-1, y-2), (x+1, y+2), (x-2, y+1), (x+2, y-1) instead of eight points
 */
public class KnightWorld {

    private TETile[][] tiles;
    // TODO: Add additional instance variables here

    public KnightWorld(int width, int height, int holeSize) {
        tiles = new TETile[width][height];
        int startx = 4;
        int starty = 0;
        // initialize with NOTHING, here I choose to use LOCKED_DOR to represent holes
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
        recursiveHoles(startx, starty, holeSize, width, height);
    }

    public void recursiveHoles(int x, int y, int holeSize, int width, int height) {
        if (x < 0 || x >= width || y < 0 || y >= height || tiles[x][y] == Tileset.FLOWER) {
            return;
        }
        for (int i = 0; i < holeSize; i++) {
            for (int j = 0; j < holeSize; j++) {
                int tempX = x + i;
                int tempY = y + j;
                if (tempX < 0 || tempX >= width
                              || tempY < 0
                              || tempY >= height
                              || tiles[tempX][tempY] == Tileset.FLOWER) {
                    continue;
                }
                tiles[tempX][tempY] = Tileset.FLOWER;
            }
        }

        recursiveHoles(x-2*holeSize, y+holeSize, holeSize, width, height);
        recursiveHoles(x-holeSize, y-2*holeSize, holeSize, width, height);
        recursiveHoles(x+holeSize, y+2*holeSize, holeSize, width, height);
        recursiveHoles(x+2*holeSize, y-holeSize, holeSize, width, height);
    }


    /** Returns the tiles associated with this KnightWorld. */
    public TETile[][] getTiles() {
        return tiles;
    }

    public static void main(String[] args) {
        // Change these parameters as necessary
        int width = 50;
        int height = 30;
        int holeSize = 4;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
}
