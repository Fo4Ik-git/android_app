package com.fo4ik.myapplication;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class dbHelper {

    public Statement statement;
    Connection connection;

    public void openDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:notify.db");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeDB() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void createDB() throws SQLException {
        File file = new File("notify.db");

        String CRAETE = "CREATE TABLE notify (\n" +
                "    id       INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    date     DATE         NOT NULL UNIQUE,\n" +
                "    text     VARCHAR      NOT NULL" +
                ")";
        statement = connection.createStatement();
        statement.execute(CRAETE);
    }

    public void createUser(String log, String pswd, String xp, String lvl, String money, String lang, String country) {
        try {
            String INSERT = "INSERT INTO users (login, password, xp, lvl, money, lang, country) " +
                    "VALUES (              '" + log + "',\n" +
                    "                      '" + pswd + "',\n" +
                    "                      '" + xp + "',\n" +
                    "                      '" + lvl + "',\n" +
                    "                      '" + money + "',\n" +
                    "                      '" + lang + "',\n" +
                    "                      '" + country + "');";
            statement = connection.createStatement();
            statement.executeUpdate(INSERT);

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            System.out.println("Error here");
        }
    }


    public ArrayList<String> getInfoUser(String login) {
        ArrayList<String> list = new ArrayList<>();
        try {
            String GET_INFO_USER = "SELECT id,\n" +
                    " login,\n" +
                    " password,\n" +
                    " xp,\n" +
                    " lvl,\n" +
                    " money,\n" +
                    " lang,\n" +
                    " country\n" +
                    " FROM users WHERE login = '" + login + "';";

            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_INFO_USER);
            while (rs.next()) {
                list.add(rs.getString("id"));
                list.add(rs.getString("login"));
                list.add(rs.getString("password"));
                list.add(rs.getString("xp"));
                list.add(rs.getString("lvl"));
                list.add(rs.getString("money"));
                list.add(rs.getString("lang"));
                list.add(rs.getString("country"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return list;
    }
}
