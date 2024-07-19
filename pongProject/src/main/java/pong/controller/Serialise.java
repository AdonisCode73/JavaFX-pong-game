package pong.controller;

import pong.model.Game;

import java.io.*;

public class Serialise implements Serializable {

    private static Serialise serialise = new Serialise();
    private String fileName;

    private Serialise(){
        fileName = "blat.ser";
    }

    public static Serialise getSerialise() {
        return serialise;
    }

    public boolean doSerialise(Game game){
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(game);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Game deSerialise(){
        Game game = null;
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            game = (Game) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }
}
