package entity;

import java.util.Scanner;

// write tests for this

/**
 * Entity Class
 * returns a Entity.Tile with a numerical key and image based on the theme
 */
public class Tile {
    private final int key;
    private boolean flipped;
    // TO-DO private String theme


    /**
     * constructor for Entity.Tile object
     */
    public Tile(int key) {         // add String "theme" as a parameter for phase 1/2

        this.key = key;
        this.flipped = false;
    }

    /**
     * @return key attribute from Tile object
     */
    public int getKey() {

        return this.key;
    }

    /**
     * sets a tile's status of flipped or un-flipped
     */
    public void setFlipped(boolean flipped) {

        this.flipped = flipped;
    }

    /**
     * @return flipped status of a tile
     */
    public boolean getFlipped() {
        return this.flipped;
    }

    @Override
    public String toString() {
        if (this.getFlipped()) {
            return Integer.toString(getKey());
        }
        else {
            return "-";
        }
    }

}
