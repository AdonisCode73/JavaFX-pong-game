package pong.model;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class Racket extends Shape implements Resizable, Serializable {

    private double width;
    private double height;

    private double racketSpeed = 10;

    public Racket(){
        this(120, 540, Color.AZURE, 30, 180);
    }

    public Racket(int posX, int posY, Color color, double width, double height){
        setPosX(posX);
        setPosY(posY);
        setColor(color);
        setWidth(width);
        setHeight(height);
    }


    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getRacketSpeed() {
        return racketSpeed;
    }

    /**
     * if posY is not at the bottom border set posY to current value + speed
     * @param dimensionY bottom border
     */
    public void moveDown(double dimensionY){
        if ( posY < dimensionY - getHeight() * 1.5) {
            setPosY(getPosY() + racketSpeed);
        }
    }

    /**
     * if posY is not at the top border set posY to current value + speed
     */
    public void moveUp(){
        if (posY > 0) {
            setPosY(getPosY() - racketSpeed);
        }
    }

    /**
     * Resizing method for screen resizing
     * @param factor set value calculated to keep everything equal
     */
    @Override
    public void resizeX(double factor) {
        this.posX = this.posX*factor;
        this.width = this.width*factor;
    }

    /**
     * Resizing method for screen resizing
     * @param factor set value calculated to keep everything equal
     */
    @Override
    public void resizeY(double factor) {
        this.posY = this.posY*factor;
        this.height = this.height*factor;
    }
}
