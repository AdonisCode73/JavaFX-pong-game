package pong.view;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import pong.controller.MenuListener;

import java.sql.SQLException;

public class MyMenu {
    private MenuBar menuBar;
    private Menu menuFile;
    private Menu menuHelp;
    private Menu menuBall;
    private Menu menuGame;
    private Menu menuRacket;
    private Menu menuPlayer;
    private Menu menuDB;
    private Menu menuSer;
    private MenuItem menuItemExit;
    private MenuItem menuItemAbout;
    private MenuItem menuItemTarget;
    private MenuItem menuItemRacketOneWidth;
    private MenuItem menuItemRacketTwoWidth;
    private MenuItem menuItemRacketOneSize;
    private MenuItem menuItemRacketTwoSize;
    private MenuItem menuItemPlayerOne;
    private MenuItem menuItemPlayerTwo;
    private MenuItem menuItemBallSpeed;
    private MenuItem menuItemBallInterval;
    private  MenuItem menuItemGameSave;
    private MenuItem menuItemGameLoad;
    private MenuItem menuItemSerialiseSave;
    private MenuItem menuItemSerialiseLoad;
    private MenuListener menuListener;

    public MyMenu(MenuListener menuListener) {
        this.menuListener = menuListener;
        menuBar = new MenuBar();
        menuFile = new Menu("File");
        menuHelp = new Menu("Help");
        menuGame = new Menu("Game");
        menuBall = new Menu("Ball");
        menuRacket = new Menu("Racket");
        menuPlayer = new Menu("Player");
        menuDB = new Menu("DB");
        menuSer = new Menu("Ser");

        menuItemExit = new MenuItem("Exit");
        menuItemAbout = new MenuItem("About");
        menuItemTarget = new MenuItem("Target Score");
        menuItemBallSpeed = new MenuItem("Ball Speed");
        menuItemBallInterval = new MenuItem("Speed Increase Interval");
        menuItemRacketOneWidth = new MenuItem("Player One Racket Width");
        menuItemRacketTwoWidth = new MenuItem("Player Two Racket Width");
        menuItemRacketOneSize = new MenuItem("Player One Racket Size");
        menuItemRacketTwoSize = new MenuItem("Player Two Racket Size");
        menuItemPlayerOne = new MenuItem("Player One Name");
        menuItemPlayerTwo = new MenuItem("Player Two Name");
        menuItemGameSave = new MenuItem("Save Game");
        menuItemGameLoad = new MenuItem("Load Game");
        menuItemSerialiseSave = new MenuItem("Save Game");
        menuItemSerialiseLoad = new MenuItem("Load Game");

        menuFile.getItems().add(menuItemExit);
        menuHelp.getItems().add(menuItemAbout);
        menuGame.getItems().add(menuItemTarget);
        menuDB.getItems().add(menuItemGameSave);
        menuDB.getItems().add(menuItemGameLoad);
        menuGame.getItems().add(menuDB);
        menuSer.getItems().add(menuItemSerialiseSave);
        menuSer.getItems().add(menuItemSerialiseLoad);
        menuGame.getItems().add(menuSer);
        menuBall.getItems().add(menuItemBallSpeed);
        menuBall.getItems().add(menuItemBallInterval);
        menuRacket.getItems().add(menuItemRacketOneWidth);
        menuRacket.getItems().add(menuItemRacketTwoWidth);
        menuRacket.getItems().add(menuItemRacketOneSize);
        menuRacket.getItems().add(menuItemRacketTwoSize);
        menuPlayer.getItems().add(menuItemPlayerOne);
        menuPlayer.getItems().add(menuItemPlayerTwo);

        menuBar.getMenus().addAll(menuFile, menuHelp, menuGame, menuBall, menuRacket, menuPlayer);
        handleClicking();
    }
         public MenuBar getMenuBar () {
            return menuBar;
        }
        public void setMenuBar (MenuBar menuBar){
            this.menuBar = menuBar;
        }

    /**
     * When a menu item is clicked the menu listener is called and the respective method is called
     */
    private void handleClicking(){

        menuItemExit.setOnAction(e -> menuListener.setExit());
        menuItemAbout.setOnAction(e -> menuListener.setAbout());

        menuItemTarget.setOnAction(e -> menuListener.setGameLimit());

        menuItemBallSpeed.setOnAction(e -> menuListener.setBallSpeed());
        menuItemBallInterval.setOnAction(e -> menuListener.setBallInterval());

        menuItemRacketOneWidth.setOnAction(e -> menuListener.setRacketWidth(1));
        menuItemRacketTwoWidth.setOnAction(e -> menuListener.setRacketWidth(2));

        menuItemRacketOneSize.setOnAction(e -> menuListener.setRacketSize(1));
        menuItemRacketTwoSize.setOnAction(e -> menuListener.setRacketSize(2));

        menuItemPlayerOne.setOnAction(e -> menuListener.setPlayerName(1));
        menuItemPlayerTwo.setOnAction(e -> menuListener.setPlayerName(2));

        menuItemGameSave.setOnAction(e -> {
            try {
                menuListener.saveGame();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        menuItemGameLoad.setOnAction(e -> {
            try {
                menuListener.loadGame();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        menuItemSerialiseSave.setOnAction(e -> menuListener.serSaveGame());

        menuItemSerialiseLoad.setOnAction(e -> menuListener.serLoadGame());
        }

}