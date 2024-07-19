package pong.model;

public interface Resizable {

    /**
     * Resizing method for screen resizing
     * @param factor set value calculated to keep everything equal
     */
    public void resizeX(double factor);

    /**
     * Resizing method for screen resizing
     * @param factor set value calculated to keep everything equal
     */
    public void resizeY(double factor);
}
