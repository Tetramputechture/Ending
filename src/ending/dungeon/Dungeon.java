package ending.dungeon;

import ending.tile.Tile;
import ending.vector.Vector2i;
import java.util.Random;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

/**
 *
 * @author Nick
 */
public class Dungeon {

    private final Vector2i size;

    private Tile[] data;

    private final Random rand;

    public Dungeon(Vector2i size) {
        rand = new Random();
        this.size = size;
        data = new Tile[size.x * size.y];
        for (int i = 0; i < data.length; i++) {
            data[i] = Tile.UNUSED;
        }
    }

    public void setCell(int x, int y, Tile celltype) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        data[x + size.x * y] = celltype;
    }

    public Tile getCell(int x, int y) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        return data[x + size.x * y];
    }

    public void setCells(int xStart, int yStart, int xEnd, int yEnd, Tile cellType) {
        assert (isXInBounds(xStart) && isXInBounds(xEnd)) : "Coordinates of cell X range out of bounds!";
        assert (isYInBounds(yStart) && isYInBounds(yEnd)) : "Coordinates of cell Y range out of bounds!";

        assert (xStart <= xEnd) : "xStart must be lower than xEnd!";
        assert (yStart <= yEnd) : "yStart must be lower than yEnd!";

        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                setCell(x, y, cellType);
            }
        }
    }

    public boolean isXInBounds(int x) {
        return x >= 0 && x < size.x;
    }

    public boolean isYInBounds(int y) {
        return y >= 0 && y < size.y;
    }

    public boolean isAreaUnused(int xStart, int yStart, int xEnd, int yEnd) {
        assert (isXInBounds(xStart) && isXInBounds(xEnd)) : "Coordinates of cell X range out of bounds!";
        assert (isYInBounds(yStart) && isYInBounds(yEnd)) : "Coordinates of cell Y range out of bounds!";

        assert (xStart <= xEnd) : "xStart must be lower than xEnd!";
        assert (yStart <= yEnd) : "yStart must be lower than yEnd!";

        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                if(getCell(x, y) != Tile.UNUSED) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isAdjacent(int x, int y, Tile tile) {
        assert (isXInBounds(x - 1) && isXInBounds(x + 1));
        assert (isYInBounds(y - 1) && isYInBounds(y + 1));

        return getCell(x - 1, y) == tile || getCell(x + 1, y) == tile
                || getCell(x, y - 1) == tile || getCell(x, y + 1) == tile;
    }
    
    public void print() {
        StringBuilder map = new StringBuilder();
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                switch (getCell(x, y)) {
                    case UNUSED: 
                        map.append(" ");
                        break;
                    case DIRTWALL:
                        map.append("█");
                        break;
                    case DIRTFLOOR:
                    case CORRIDOR:
                        map.append(".");
                        break;
                    case DOOR:
                        map.append("+");
                        break;
                    case UPSTAIRS:
                        map.append("<");
                        break;
                    case DOWNSTAIRS:
                        map.append(">");
                        break;
                }
            }
            map.append("\n");
        }
        map.append("\n");
        
        System.out.println(map.toString());
    }
    
    public void draw(RenderWindow rw) {
        Sprite[][] sprites = new Sprite[size.x][size.y];
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                Sprite s = new Sprite();
                switch (getCell(x, y)) {
                    case UNUSED: 
                        s.setTexture(Tile.UNUSED_TEXTURE);
                        break;
                    case DIRTWALL:
                        s.setTexture(Tile.STONEWALL_TEXTURE);
                        break;
                    case DIRTFLOOR:
                    case CORRIDOR:
                        s.setTexture(Tile.CORRIDOR_TEXTURE);
                        break;
                    case DOOR:
                        s.setTexture(Tile.DOOR_TEXTURE);
                        break;
                    case UPSTAIRS:
                        s.setTexture(Tile.UPSTAIRS_TEXTURE);
                        break;
                    case DOWNSTAIRS:
                        s.setTexture(Tile.DOWNSTAIRS_TEXTURE);
                        break;
                }
                s.setPosition(x*16, y*16);
                sprites[x][y] = s;
            }
        }
        
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                rw.draw(sprites[x][y]);
            }
        }
        
    }
}
