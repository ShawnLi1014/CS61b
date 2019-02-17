package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public static class Position {
        public int x;
        public int y;

        private Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    /** Retrun the width of the rows in a hexagon
     *
     * @param s the size of the hex
     * @param i the row number, i = 0 is the bottom line
     * @return hexRowWidth
     */
    private static int hexRowWidth(int s, int i) {
        if(i < s) {
            return s + 2 * i;
        }
        return s + 2 * (2 * s -1 - i);
    }

    private static int hexRowOffset(int s, int i) {
        if(i < s) {
            return - i;
        }
        return i - 2 * s + 1;
    }

    private static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for(int x = 0; x < width; x++) {
            int xCoord = p.x + x;
            int yCoord = p.y;
            world[xCoord][yCoord] = t;
        }
    }

    public static void addHeaxgon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        for(int i = 0; i < 2 * s; i++) {
            int thisRowY = p.y + i;
            int thisRowX = p.x + hexRowOffset(s, i);
            Position thisRowPosition = new Position(thisRowX, thisRowY);
            int thisRowWidth = hexRowWidth(s, i);
            addRow(world, thisRowPosition, thisRowWidth, t);
        }
    }

    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(7, hexRowWidth(3, 3));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(3, 1));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(2, hexRowWidth(2, 0));
        assertEquals(4, hexRowWidth(2, 1));
        assertEquals(4, hexRowWidth(2, 2));
        assertEquals(2, hexRowWidth(2, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(0, hexRowOffset(3, 5));
        assertEquals(-1, hexRowOffset(3, 4));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-1, hexRowOffset(3, 1));
        assertEquals(0, hexRowOffset(3, 0));
        assertEquals(0, hexRowOffset(2, 0));
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(-1, hexRowOffset(2, 2));
        assertEquals(0, hexRowOffset(2, 3));
    }

    public static void main(String [] args) {
        int WIDTH = 50;
        int HEIGHT = 50;

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Position p = new Position(10, 10);
        addHeaxgon(world, p, 5, Tileset.WALL );

        ter.renderFrame(world);

    }
}
