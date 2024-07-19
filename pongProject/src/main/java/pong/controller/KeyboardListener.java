package pong.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pong.model.Game;
import pong.view.LabCanvas;

public class KeyboardListener  implements EventHandler <KeyEvent> {

    private Game game;
    private LabCanvas canvas;

    public KeyboardListener(Game game2, LabCanvas canvas){
        this.game = game2;
        this.canvas = canvas;
    }

    /**
     * Determines if a key is pressed and what the value of the key is, performs an action if keyCode = key
     * @param keyEvent when a key is pressed
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        System.out.println(keyEvent);

        KeyCode key = keyEvent.getCode();
        if (KeyCode.UP.equals(key))
        {
            game.getPlayer2().getRacket().moveUp();
        }
        if (KeyCode.DOWN.equals(key))
        {
            game.getPlayer2().getRacket().moveDown(game.getDimensionY());
        }
        if (KeyCode.W.equals(key))
        {
            game.getPlayer1().getRacket().moveUp();
        }
        if (KeyCode.S.equals(key))
        {
            game.getPlayer1().getRacket().moveDown(game.getDimensionY());
        }
        if (KeyCode.P.equals(key)){
            game.pauseGame();
        }
        if (KeyCode.R.equals(key)){
            game.resumeGame();
        }
        if (KeyCode.ESCAPE.equals(key)){
            game.restartGame();
        }
        canvas.drawGame(game);
    }
}
