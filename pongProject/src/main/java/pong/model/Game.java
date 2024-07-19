package pong.model;


import javafx.scene.paint.Color;

import java.io.Serializable;


public class Game implements Resizable, Serializable {

    private String name;
    private Ball ball;
    private Player player1;
    private Player player2;
    private int winningScore;
    private double[] previousSettings = new double[2];
    private double dimensionX;
    private double dimensionY;
    private transient Color colour = Color.YELLOWGREEN;


    public Game(){
        player1 = new Player();
        player2 = new Player();
        player2.getRacket().setPosX(1770);
        player2.getRacket().setColor(colour);
        player2.getPlayerName().setPositionX(1700);
        player2.getPlayerName().setColour(colour);
        ball = new Ball();
        winningScore = 15;
        dimensionX = 1920;
        dimensionY = 1080;
        name = "Pong";
    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTarget(int winningScore) {
        this.winningScore = winningScore;
    }

    public int getTarget(){
        return winningScore;
    }

    public void setDimensionX(double dimensionX) {
        this.dimensionX = dimensionX;
    }

    public void setDimensionY(double dimensionY) {
        this.dimensionY = dimensionY;
    }

    public double getDimensionX() {
        return dimensionX;
    }

    public double getDimensionY() {
        return dimensionY;
    }

    /**
     * Fills array with previous ball and racket speed and sets current speed to 0
     */
    public void pauseGame(){
        previousSettings = new double[] {ball.getStepX(), player1.getRacket().getRacketSpeed()};
        player1.getRacket().setSpeed(0);
        player2.getRacket().setSpeed(0);
        ball.setStepX(0);
        ball.setStepY(0);
    }

    /**
     * Sets ball and racket speed with previous value from array
     */
    public void resumeGame(){
        player1.getRacket().setSpeed((int)previousSettings[1]);
        player2.getRacket().setSpeed((int)previousSettings[1]);
        ball.setStepX(previousSettings[0]);
        ball.setStepY(previousSettings[0]);
    }

    /**
     * Sets values back to original
     */
    public void restartGame(){
        player1.getRacket().setPosY(540);
        player1.setScore(0);
        player2.getRacket().setPosY(540);
        player2.setScore(0);
        ball.resetBall(960, 540);
        ball.setSpeed(4);
    }



    /**
     * Resizing method for screen resizing
     * @param factor set value calculated to keep everything equal
     */
    @Override
    public void resizeX(double factor) {
        player1.getRacket().resizeX(factor);
        player2.getRacket().resizeX(factor);
        ball.resizeX(factor);
        player1.getPlayerName().resizeX(factor);
        player2.getPlayerName().resizeX(factor);
    }

    /**
     * Resizing method for screen resizing
     * @param factor set value calculated to keep everything equal
     */
    @Override
    public void resizeY(double factor) {
        player1.getRacket().resizeY(factor);
        player2.getRacket().resizeY(factor);
        ball.resizeY(factor);
        player1.getPlayerName().resizeY(factor);
        player2.getPlayerName().resizeY(factor);
    }
}
