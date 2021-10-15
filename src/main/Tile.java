/**
 * Entity Class
 * returns a Tile with a numerical key and image based on the theme
 * @param theme
 * @param key
 */

public class Tile {

    private int key;
    // private String theme


    // constructor for Tile object
    public Tile(int key) {         // add String theme as a parameter
        this.key = key;
    }

    public int getKey () {
        return this.key;
    }
}
