package pong.controller;

import pong.model.Ball;
import pong.model.Game;
import pong.model.Player;
import pong.model.Racket;
import pong.view.LabCanvas;

public class BallManager implements Runnable{

    private Game game;
    private LabCanvas canvas;

    private int interval = 0;

    public BallManager(Game game, LabCanvas canvas){
        this.game = game;
        this.canvas = canvas;
    }
    @Override
    public void run() {
        Ball ball = game.getBall();
        int counter=0;
        while(true)
        {
            counter++;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ball.move();

            // player goal detection
            if (ball.getPosX() <= 0)
            {
                game.getPlayer2().setScore(game.getPlayer2().getScore() + 1);
                goalDetection(2);
                interval = 0;
            }
            if (ball.getPosX() >= game.getDimensionX())
            {
                game.getPlayer1().setScore(game.getPlayer1().getScore() + 1);
                goalDetection(1);
                interval = 0;
            }

            // ball speed interval increase
            if (interval == ball.getInterval()){
                intervalIncrease(game.getBall());
                interval = 0;
            }

            // ball bouncing logic
            checkCollisionTop(ball);

            checkCollisionBottom(ball);

            checkCollisionRacketOne(game.getPlayer1().getRacket(), ball);

            checkCollisionRacketTwo(game.getPlayer2().getRacket(), ball);

            canvas.drawGame(game);
        }

    }

    /**
     * Detecting goal scored and determines if game has ended
     * @param player player object to identify scorer/winner
     */
    public void goalDetection(int player){
        Player winner = game.getPlayer1();
        if (player == 2){
            winner = game.getPlayer2();
        }
        if (checkEndOfGame(game)){
            canvas.drawWinner(canvas.getGraphicsContext2D(), game, winner);
            game.restartGame();
        }
        else{
            canvas.drawGoal(canvas.getGraphicsContext2D(), game, winner);
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        game.getBall().resetBall(game.getDimensionX()/2, game.getDimensionY()/2);
    }

    /**
     * Increase speed of ball once interval count has been reached
     * @param ball ball object
     */
    public void intervalIncrease(Ball ball){
        ball.updateSpeed(ball.getStepX() * 1.2);
    }

    /**
     * Determines if player 2 has scored i.e. ball is in player 1 border
     * @param game game object where ball is stored
     * @return True or false if ball posX is less than 10 or not
     */
    public boolean player2Scores(Game game) {
        return game.getBall().getPosX()<10;
    }

    /**
     * Determines if the game is over i.e. max score has been reached
     * @param game game object where player scores are stored
     * @return True or false if target score is reached or not
     */
    public boolean checkEndOfGame(Game game)
    {
        int maxScore= Math.max(game.getPlayer1().getScore(), game.getPlayer2().getScore());
        return game.getTarget()<=maxScore;
    }

    /**
     * Racket 1 Collision detection method
     * @param racket player 1 racket object
     * @param ball ball object
     */

    public void checkCollisionRacketOne(Racket racket, Ball ball){
        if (ball.getPosX() <= game.getPlayer1().getRacket().getPosX() +
                game.getPlayer1().getRacket().getWidth() && ball.getPosY()  >= game.getPlayer1().getRacket().getPosY()
                && ball.getPosY()  <= game.getPlayer1().getRacket().getPosY() +
                game.getPlayer1().getRacket().getHeight()){
            ball.setStepX(-ball.getStepX());
            interval++;
        }
    }

    /**
     * Racket 2 Collision detection method
     * @param racket player 2 racket object
     * @param ball ball object
     */
    public void checkCollisionRacketTwo(Racket racket, Ball ball){
        if (ball.getPosX() + (ball.getRadius() * 2) >= game.getPlayer2().getRacket().getPosX() + game.getPlayer2().getRacket().getWidth() &&
                ball.getPosY() >= game.getPlayer2().getRacket().getPosY() &&
                ball.getPosY() <= game.getPlayer2().getRacket().getPosY() +
                        game.getPlayer2().getRacket().getHeight()){
            ball.setStepX(-ball.getStepX());
            interval++;
        }
    }

    /**
     * Top border collision detection method
     * @param ball ball object
     */
    public void checkCollisionTop(Ball ball){
        if (ball.getPosY() <= 0){
            ball.setStepY(-ball.getStepY());
            interval++;
        }
    }

    /**
     * Bottom border collision detection method
     * @param ball ball object
     */
    public void checkCollisionBottom(Ball ball){
        if (ball.getPosY() + (ball.getRadius() * 3) >= game.getDimensionY()){
            ball.setStepY(-ball.getStepY());
            interval++;
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
