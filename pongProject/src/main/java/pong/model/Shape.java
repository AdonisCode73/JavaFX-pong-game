package pong.model;

import javafx.scene.paint.Color;

abstract class Shape {
    protected double posX;

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosX() {
        return posX;
    }

    protected double posY;

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getPosY() {
        return posY;
    }

    private int speed;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    private transient Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
