package pong.controller;

import pong.model.Game;

import java.sql.*;

public class DatabaseManager {
    public Game getGame() throws ClassNotFoundException, SQLException
    {
        Connection connection = DatabaseConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from game",
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs= statement.executeQuery();
        rs.last();
        rs.relative(-1);
        while(rs.next())
        {
            System.out.println(rs.getString("player1Name"));
            System.out.println(rs.getString("player2Name"));
            GameBuilder builder = new GameBuilder().
                    withName(rs.getString("name")).
                    withPlayer1Name(rs.getString("player1Name")).
                    withPlayer2Name(rs.getString("player2Name")).
                    withPlayer1Score(rs.getInt("player1Score")).
                    withPlayer2Score(rs.getInt("player2Score")).
                    withTarget(rs.getInt("target"));
            Game game = builder.build();
            return game;
        }
        return null;
    }

    public boolean insertGame(Game game) throws ClassNotFoundException, SQLException{
        Connection connection = DatabaseConnector.getConnection();
        Statement statement = connection.createStatement();

        String insertGameData = "INSERT INTO game (player1Name, player2Name, player1Score, player2Score, name, target) " +
                "VALUES ('" + game.getPlayer1().getPlayerName().getName() + "', '" +
                game.getPlayer2().getPlayerName().getName() + "', " + game.getPlayer1().getScore() + ", "
                + game.getPlayer2().getScore() + ", '"
                + game.getName() + "', "
                + game.getTarget() + ");";

        System.out.println(insertGameData);

        int insertion_completed = statement.executeUpdate(insertGameData);

        return insertion_completed != 0;

    }
}
