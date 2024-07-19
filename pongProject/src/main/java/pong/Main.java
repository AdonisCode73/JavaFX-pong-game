package pong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pong.controller.MenuListener;
import pong.controller.*;
import pong.model.*;
import pong.view.*;

import java.sql.SQLException;


public class Main extends Application {
     private Game game;

    private LabCanvas canvas = new LabCanvas(1920, 1080);
     private GameController gameController = new GameController();
     private MenuListener menuListener;
     private MyMenu myMenu;
     private DatabaseManager dbManager;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public  void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
        dbManager= new DatabaseManager();
        try {
            game = dbManager.getGame();
        } catch (ClassNotFoundException | SQLException e) {
            game = new Game();
            e.printStackTrace();
        }
        gameController.setGame(game);
        BallManager ballManager= new BallManager(gameController.getGame(), canvas);
        menuListener = new MenuListener(gameController.getGame(), canvas, gameController, ballManager);
        myMenu = new MyMenu(menuListener);
        BorderPane root = new BorderPane();
        KeyboardListener keyboardListener = new KeyboardListener( gameController.getGame(), canvas);
        canvas.setOnKeyPressed(keyboardListener );
        canvas.setOnKeyTyped(keyboardListener );
        canvas.setFocusTraversable(true);
        Thread thread = new Thread(ballManager);
        thread.start();
        Thread.yield();

        canvas.drawGame(game);
        root.setCenter(canvas);
        root.setTop(myMenu.getMenuBar());
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(310);
        primaryStage.setMinWidth(410);
        primaryStage.widthProperty().addListener(observable -> {
            double factor= primaryStage.getWidth()/gameController.getGame().getDimensionX();
            gameController.getGame().setDimensionX(primaryStage.getWidth());
            gameController.getGame().resizeX(factor);
            canvas.drawGame(game);
        });
        primaryStage.heightProperty().addListener(observable -> {
            double factor= primaryStage.getHeight()/gameController.getGame().getDimensionY();
            gameController.getGame().setDimensionY(primaryStage.getHeight());
            gameController.getGame().resizeY(factor);
            canvas.drawGame(game);

        });
        primaryStage.show();

    }
}
