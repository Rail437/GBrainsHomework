package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import static sample.ChatConstants.*;
import static sample.ClientHandler.sendMsg;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbconnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" +
                dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(String nick, String login, String password){

        String insert = "INSERT INTO " + USER_TABLE + " (" + USER_NICK + "," +
                        USER_LOGIN + "," + USER_PASSWORD + ") " +
                        " VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(insert);
            preparedStatement.setString(1, nick);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            sendMsg("Добавлен клиент с ником: " + nick);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> searchForUser(){
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + USER_TABLE ;
        List<User> listUsers = new ArrayList();
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listUsers.add(new User(
                        resultSet.getString(USER_NICK),
                        resultSet.getString(USER_LOGIN),
                        resultSet.getString(USER_PASSWORD)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    public List getNicks(){
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + USER_TABLE ;
        List listNicks = new ArrayList();
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listNicks.add(resultSet.getString(USER_NICK));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listNicks;
    }

    public ResultSet getUser(){
        ResultSet resSet = null;

        return resSet;
    }
}
