package Interfaz.InterfazUtils;

/**
 * Clase para soportar coordenadas
 */
public class Coordinates {

    private int north;
    private int south;
    private int east;
    private int west;

    /**
     * Coordinates
     * 
     * @param north NORTH
     * @param south SOUTH
     * @param east  EAST
     * @param west  WEST
     */
    public Coordinates(int north, int south, int east, int west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    /**
     * @return int return the north
     */
    public int getNorth() {
        return north;
    }

    /**
     * @param north the north to set
     */
    public void setNorth(int north) {
        this.north = north;
    }

    /**
     * @return int return the south
     */
    public int getSouth() {
        return south;
    }

    /**
     * @param south the south to set
     */
    public void setSouth(int south) {
        this.south = south;
    }

    /**
     * @return int return the east
     */
    public int getEast() {
        return east;
    }

    /**
     * @param east the east to set
     */
    public void setEast(int east) {
        this.east = east;
    }

    /**
     * @return int return the west
     */
    public int getWest() {
        return west;
    }

    /**
     * @param west the west to set
     */
    public void setWest(int west) {
        this.west = west;
    }
}
