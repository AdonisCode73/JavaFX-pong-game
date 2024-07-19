

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pong.controller.BallManager;
import pong.model.Ball;
import pong.model.Game;
import pong.model.Racket;
import pong.view.LabCanvas;


public class BallManagerTest {
    Game game = new Game();
    LabCanvas canvas = new LabCanvas(600, 600);
    BallManager manager = new BallManager(game, canvas);
    @Before
    public void initialise()
    {
        game.setDimensionX(600);
        game.setDimensionY(600);
        game.setTarget(10);
    }
    @Test
    public void testEndoFGame() {
        game.getPlayer2().setScore(7);
        game.getPlayer1().setScore(11);
// score is 11-7 up to 10
        assertTrue(manager.checkEndOfGame(game));
// score is 7-7 up to 10
        game.getPlayer1().setScore(7);
        assertFalse(manager.checkEndOfGame(game));
    }
    @Test
    public void testGoalPlayer2()
    {
        game.getBall().setPosX(1);
        assertTrue(manager.player2Scores(game));
        game.getBall().setPosX(100);
        assertFalse(manager.player2Scores(game));
    }
    @Test
    public void testCollisionRacketOne(){
        Ball ball = game.getBall();
        ball.setPosX(60);
        ball.setPosY(200);
        ball.setStepX(-5);

        Racket racketOne = game.getPlayer1().getRacket();
        racketOne.setPosX(45);
        racketOne.setPosY(150);
        racketOne.setHeight(100);
        racketOne.setWidth(10);

        ball.move();

        manager.checkCollisionRacketOne(racketOne, ball);

        assertTrue(ball.getStepX() > 0);

        ball.setPosX(600);
        ball.setPosY(500);
        ball.setStepX(5);

        ball.move();

        manager.checkCollisionRacketOne(racketOne, ball);

        assertFalse(ball.getStepX() < 0);
    }
    @Test
    public void testCollisionRacketTwo(){
        Ball ball = game.getBall();
        ball.setPosX(60);
        ball.setPosY(200);
        ball.setStepX(5);

        Racket racketTwo = game.getPlayer2().getRacket();
        racketTwo.setPosX(75);
        racketTwo.setPosY(150);
        racketTwo.setHeight(100);
        racketTwo.setWidth(10);

        ball.move();

        manager.checkCollisionRacketTwo(racketTwo, ball);

        assertTrue(ball.getStepX() < 0);

        ball.setPosX(600);
        ball.setPosY(500);
        ball.setStepX(5);

        ball.move();

        manager.checkCollisionRacketTwo(racketTwo, ball);

        assertFalse(ball.getStepX() < 0);
    }

    @Test
    public void testTopCollision(){
        Ball ball = game.getBall();
        ball.setPosX(50);
        ball.setPosY(5);
        ball.setStepY(-5);

        ball.move();

        manager.checkCollisionTop(ball);

        assertTrue(ball.getStepY() > 0);

        ball.setPosY(67);

        ball.move();

        manager.checkCollisionTop(ball);

        assertFalse(ball.getStepY() < 0);
    }

    @Test
    public void testBottomCollision(){
        Ball ball = game.getBall();
        ball.setPosX(50);
        ball.setPosY(game.getDimensionY()- 5);
        ball.setStepY(5);

        ball.move();

        manager.checkCollisionBottom(ball);

        assertTrue(ball.getStepY() < 0);

        ball.setPosY(67);

        ball.move();

        manager.checkCollisionBottom(ball);

        assertFalse(ball.getStepY() > 0);
    }
}