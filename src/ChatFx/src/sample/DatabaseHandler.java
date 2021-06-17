package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import static sample.ChatConstants.*;

public class DatabaseHandler extends Config{
    private static final Logger LOGGER = Logger.getLogger(MyServer.class.getName());
    Connection dbConnection;

    public Connection getDbconnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" +
                dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver" );
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(ClientHandler client, String nick, String login, String password){

        String insert = "INSERT INTO " + USER_TABLE + " (" + USER_NICK + "," +
                USER_LOGIN + "," + USER_PASSWORD + ") " +
                " VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(insert);
            preparedStatement.setString(1, nick);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            client.sendMsg("Добавлен клиент с ником: " + nick);
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changeNickname(ClientHandler client,String nick, String changeNick){
        String update = "UPDATE " + USER_TABLE + " SET NICKNAME=" +
                "'" + changeNick + "'" + " WHERE NICKNAME=" + "'"+ nick + "'";
        System.out.println(update);
        try {
            PreparedStatement statement = getDbconnection().prepareStatement(update);
            statement.executeUpdate();
            client.sendMsg("Логин: " + nick + " был изменен на " + changeNick);
            statement.close();
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
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    public List getNicks(){
        String select = "SELECT * FROM " + USER_TABLE ;
        List listNicks = new ArrayList();
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listNicks.add(resultSet.getString(USER_NICK));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listNicks;
    }

    public List getLogin(){
        String select = "SELECT * FROM " + USER_TABLE ;
        List listNicks = new ArrayList();
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listNicks.add(resultSet.getString(USER_LOGIN));
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listNicks;
    }

    public ResultSet searchForUserTest(){
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + USER_TABLE ;
        try {
            LOGGER.log(Level.INFO,"Вызвали поиск юзера из базы данных");
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            //preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
