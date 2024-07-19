package pong.controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.w3c.dom.Text;
import pong.model.Game;
import pong.view.LabCanvas;

import java.sql.SQLException;
import java.util.Optional;

public class MenuListener {

    private Game game;
    private LabCanvas canvas;

    private GameController gameController;
    private BallManager ballManager;

    public MenuListener(Game game, LabCanvas canvas, GameController gameController, BallManager ballManager){
        this.game = game;
        this.canvas = canvas;
        this.gameController = gameController;
        this.ballManager = ballManager;
    }

    /**
     * exits the application
     */
    public void setExit(){
        Platform.exit();
    }

    /**
     * Creates and fills alert box
     */
    public void setAbout() {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PONG PONG PONG");
        alert.setHeaderText("Made in MTU");
        alert.setContentText("Crafted by Sir Adam Allman");
        alert.showAndWait().ifPresent((btnType) -> {});
    }

    /**
     * Sets the target score for the game
     */
    public void setGameLimit(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Game Limit");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(string -> {
            game.setTarget(Integer.parseInt(result.get()));
        });
    }

    /**
     * Sets the speed of the ball
     */
    public void setBallSpeed(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ball Speed");
        dialog.setHeaderText("Enter speed");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(string -> {
            game.getBall().updateSpeed(Integer.parseInt(result.get()));
        });
    }


    /**
     * Sets the number of bounces a ball makes before increasing speed
     */
    public void setBallInterval(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ball Interval Speed");
        dialog.setHeaderText("Enter ball interval");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(string -> {
            game.getBall().setInterval(Integer.parseInt(result.get()));
        });

    }

    /**
     * Sets the player rackets width
     * @param player player whose racket will be adjusted
     */
    public void setRacketWidth(int player){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Racket Width");
        dialog.setHeaderText("Enter width");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(string -> {
            if (player == 1)  game.getPlayer1().getRacket().setWidth(Integer.parseInt(result.get()));
            else  game.getPlayer2().getRacket().setWidth(Integer.parseInt(result.get()));
        });
        canvas.drawGame(game);
    }

    /**
     * Dialogue box to set the players name
     * @param player player whose name will be set
     */
    public void setPlayerName(int player){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Player Name");
        dialog.setHeaderText("Enter a name");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(string -> {
        if (player == 1)  game.getPlayer1().getPlayerName().setName((result.get()));
        else game.getPlayer2().getPlayerName().setName(result.get());
        });
        canvas.drawGame(game);
    }

    /**
     * Dialogue box to set the height of the racket
     * @param size size of which the racket is to be set to
     */
    public void setRacketSize(int size){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Racket Size");
        dialog.setHeaderText("Enter size");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(string -> {
        if (size == 1) game.getPlayer1().getRacket().setHeight(Integer.parseInt(result.get()));
        else game.getPlayer2().getRacket().setHeight(Integer.parseInt(result.get()));
        });
        canvas.drawGame(game);
    }

    public void saveGame() throws ClassNotFoundException, SQLException {
        game.pauseGame();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Save Game");
        dialog.setHeaderText("Enter saved game name");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(string -> {
            game.setName(result.get());
        });
        DatabaseManager databaseManager = new DatabaseManager();
        boolean inserted;
        inserted = databaseManager.insertGame(game);

        if (inserted){
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved");
            alert.setHeaderText("Game Saved");
            alert.setContentText("Game successfully saved");
            alert.showAndWait().ifPresent((btnType) -> {});
        }
        else{
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed");
            alert.setHeaderText("Unable to save");
            alert.setContentText("Experienced error and unable to save");
            alert.showAndWait().ifPresent((btnType) -> {});
        }
        game.resumeGame();
    }

    public void loadGame() throws ClassNotFoundException, SQLException{
        DatabaseManager databaseManager = new DatabaseManager();

        game = databaseManager.getGame();

        if (game != null){
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Loaded");
            alert.setHeaderText("Game Loaded");
            alert.setContentText("Game successfully Loaded");
            alert.showAndWait().ifPresent((btnType) -> {});
            canvas.drawGame(game);
            gameController.setGame(game);
            ballManager.setGame(game);

        }

        else{
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed to load");
            alert.setHeaderText("Failed");
            alert.setContentText("Game failed to load");
            alert.showAndWait().ifPresent((btnType) -> {});
        }
    }

    public void serSaveGame(){
        Serialise serialize = Serialise.getSerialise();
        boolean saved = serialize.doSerialise(game);
        if (saved) {
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved");
            alert.setHeaderText("Game Saved");
            alert.setContentText("Game successfully saved using serialization");
            alert.showAndWait().ifPresent((btnType) -> {});
        }
        else {
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to Save Game");
            alert.setContentText("An error occurred while saving the game");
            alert.showAndWait().ifPresent((btnType) -> {});
        }
    }

    public void serLoadGame(){
        Serialise serialize = Serialise.getSerialise();
        Game loadGame = serialize.deSerialise();
        if (loadGame != null) {
            game = loadGame;
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Loaded");
            alert.setHeaderText("Game Loaded");
            alert.setContentText("Game successfully loaded using serialization");
            alert.showAndWait().ifPresent((btnType) -> {});
            canvas.drawGame(game);
            gameController.setGame(game);
            ballManager.setGame(game);
        } else {
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to Load Game");
            alert.setContentText("An error occurred while loading the game");
            alert.showAndWait().ifPresent((btnType) -> {});
        }
    }


}
