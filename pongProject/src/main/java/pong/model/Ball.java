package pong.model;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class Ball extends Shape implements Resizable, Serializable {

    private double radius;
    private int interval;
    private double stepY;
    private double stepX;

    public Ball(){
        this(960, 540, 4, Color.FIREBRICK, 50, 16);
    }

    public Ball(int posX, int posY, int speed, Color color, double radius, int interval){
        setPosX(posX);
        setPosY(posY);
        setSpeed(speed);
        setColor(color);
        setRadius(radius);
        setInterval(interval);
        setStepY(speed);
        setStepX(speed);
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getInterval() {
        return interval;
    }

    public void updateSpeed(double speed){
        setStepY(speed);
        setStepX(speed);
    }

    public void setStepY(double stepY) {
        this.stepY = stepY;
    }

    public double getStepY() {
        return stepY;
    }

    public void setStepX(double stepX) {
        this.stepX = stepX;
    }

    public double getStepX() {
        return stepX;
    }

    /**
     * Calls stepX/Y methods to invoke Y and X movement
     */
    public void move(){
            stepY();
            stepX();
    }

    /**
     * Sets posY as the current position + stepY (movement speed)
     */
    public void stepY(){
        setPosY(getPosY() + getStepY());
    }

    /**
     * Sets posX as the current position + stepX (movement speed)
     */
    public void stepX(){
        setPosX(getPosX() + getStepX());
    }

    /**
     * Reset the ball back to original position
     * @param x x position value for ball
     * @param y y position value for ball
     */
    public void resetBall(double x, double y){
        posX = x;
        posY = y;
    }

    /**
     * Resizing method for screen resizing
     * @param factor set value calculated to keep everything equal
     */
    @Override
    public void resizeX(double factor) {
        this.posX = this.posX*factor;
        this.radius = this.radius*factor;
    }

    /**
     * Resizing method for screen resizing
     * @param factor set value calculated to keep everything equal
     */
    @Override
    public void resizeY(double factor) {
        this.posY = this.posY*factor;
        this.radius = this.radius*factor;
    }
}
