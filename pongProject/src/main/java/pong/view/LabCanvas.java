package pong.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pong.model.Game;
import pong.model.Ball;
import pong.model.Player;
import pong.model.Racket;

public class LabCanvas extends Canvas {

    public LabCanvas(double width, double height) {
        super(width, height);

        GraphicsContext gc = this.getGraphicsContext2D();

    }

    /**
     * Draws the background of the application
     * @param gc necessary javafx import object
     */
    private void drawBackground(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    /**
     * Draws the ball
     * @param gc necessary javafx import object
     * @param ball ball object to be drawn
     */
    private void drawBall(GraphicsContext gc, Ball ball){
        gc.setFill(ball.getColor());
        gc.fillOval(ball.getPosX(), ball.getPosY(), ball.getRadius(), ball.getRadius());
    }

    /**
     * Draws the racket
     * @param gc necessary javafx import object
     * @param racket racket object to be drawn
     */
    private void drawRacket(GraphicsContext gc, Racket racket){
        gc.setFill(racket.getColor());
        gc.fillRect(racket.getPosX(), racket.getPosY(), racket.getWidth(), racket.getHeight());
    }

    /**
     * Draws the player names
     * @param gc necessary javafx import object
     * @param player player object whos name will be drawn
     */
    private void drawPlayer(GraphicsContext gc, Player player){
        gc.setFill(player.getPlayerName().getColour());
        gc.setFont(new Font("Comic Sans MS", player.getPlayerName().getFontSize()));
        gc.fillText(player.getPlayerName().getName() + ": " + player.getScore(), player.getPlayerName().getPositionX(), 70);
    }

    /**
     * Text that is drawn when a player scores a goal
     * @param gc necessary javafx import object
     * @param game game object which contains the dimensions
     * @param player player object to determine which player scored
     */
    public void drawGoal(GraphicsContext gc, Game game, Player player){
        gc.setFill(Color.CORNFLOWERBLUE);
        gc.setFont(new Font("Comic Sans MS", 70));
        gc.fillText("GOALASO!!!!!! " + player.getPlayerName().getName(), game.getDimensionX()/2 - 200,
                game.getDimensionY()/2 - 200);
    }

    /**
     * Text drawn when someone wins the game
     * @param gc necessary javafx import object
     * @param game game object which contains the dimensions
     * @param winner player object to determine which player scored
     */
    public void drawWinner(GraphicsContext gc, Game game, Player winner){
        gc.setFill(Color.CRIMSON);
        gc.setFont(new Font("Comic Sans MS", 70));
        gc.fillText("WINNER WINNER CHICKEN DINNER!!!!" + winner.getPlayerName().getName(),
                game.getDimensionX()/2 - 200, game.getDimensionY()/2 - 200);
    }

    /**
     * Method to draw everything together
     * @param game game object which stores the game
     */
    public void drawGame(Game game){
        GraphicsContext gc = this.getGraphicsContext2D();
        drawBackground(gc);
        drawBall(gc, game.getBall());
        drawRacket(gc, game.getPlayer1().getRacket());
        drawRacket(gc, game.getPlayer2().getRacket());
        drawPlayer(gc, game.getPlayer1());
        drawPlayer(gc, game.getPlayer2());
    }
}
