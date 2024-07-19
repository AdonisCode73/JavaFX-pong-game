package pong.controller;
import javafx.scene.paint.Color;
import pong.model.Game;
import pong.model.Player;
import pong.model.PlayerName;

public class GameBuilder {
    private String player1Name;
    private String player2Name;
    private int player1Score;
    private int player2Score;
    private String name;
    private int target;
    public Game build() {
        Game game = new Game();
        Player player1= new Player();
        Player player2= new Player();
        player1.getPlayerName().setName(player1Name);
        player2.getPlayerName().setName(player2Name);
        player1.setScore(player1Score);
        player2.setScore(player2Score);
        player2.getPlayerName().setPositionX(1700);
        player2.getRacket().setPosX(1770);
        player2.getRacket().setColor(Color.PAPAYAWHIP);
        player2.getPlayerName().setColour(Color.YELLOWGREEN);
        game.setTarget(target);
        game.setName(name);
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        return game;
    }
    public GameBuilder withName(String name) {
        this.name=name;
        return this;
    }
    public GameBuilder withPlayer1Name(String p1Name) {
        this.player1Name=p1Name;
        return this;
    }
    public GameBuilder withPlayer2Name(String p2Name) {
        this.player2Name=p2Name;
        return this;
    }
    public GameBuilder withPlayer1Score(int score) {
        this.player1Score=score;
        return this;
    }
    public GameBuilder withPlayer2Score(int score) {
        this.player2Score=score;
        return this;
    }
    public GameBuilder withTarget(int t) {
        this.target=t;
        return this;
    }
}
