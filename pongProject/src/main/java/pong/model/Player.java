package pong.model;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class Player implements Serializable {

    private PlayerName name;
    private Racket racket;
    private int score;

    public Player(){
        this(new PlayerName());

    }

    public Player(PlayerName name){
        this.name = name;
        racket = new Racket();
    }

    public PlayerName getPlayerName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setRacket(Racket racket) {
        this.racket = racket;
    }

    public Racket getRacket() {
        return racket;
    }

}
