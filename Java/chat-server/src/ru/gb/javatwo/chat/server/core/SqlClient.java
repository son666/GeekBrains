package ru.gb.javatwo.chat.server.core;

import java.sql.*;

public class SqlClient {

    private static Connection connection;
    private static Statement statement;

    synchronized static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chat-server/chat_db_sqlite.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static String getNickname(String login, String password) {
        String query = String.format("select nickname from users where login='%s' and password='%s'", login, password);
        try (ResultSet set = statement.executeQuery(query)) {
            if (set.next())
                return set.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    synchronized static String changeNickname(String login, String newNickName, String password) {
        String query = String.format("UPDATE users SET nickname = '%s' WHERE login = '%s' AND password = '%s'", newNickName, login, password);
        try {
            int count = statement.executeUpdate(query);
            if (count > 0) {
                return newNickName;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    synchronized static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
