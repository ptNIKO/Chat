package sample.dao;

import sample.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO{
    // Get and Add user
    private Connection con = null;
    private PreparedStatement peparedStatement = null;
    private int result;
    private ResultSet resultSet;

    public UserDAO (){
        String URL = "jdbc:mysql://localhost:3306/mydbtest"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        String USERNAME = "root";
        String PASSWORD = "root";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (!conn.isClosed())
                System.out.println("Соединение с БД установлено");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }

        con = conn;
    }

    public void AddUser(User user){
        try {
            // query
            String sql = "insert into mydbtest.user (Name,Password,NickName) values (?,?,?)";
            peparedStatement = con.prepareStatement(sql);
            peparedStatement.setString(1, user.getLogin());
            peparedStatement.setString(2, user.getPassword());
            peparedStatement.setString(3, user.getNickName());
            result = peparedStatement.executeUpdate();

            if (result == 1) {
                System.out.println("Вы зарегестрированы");
            } else
                System.out.println("Вы не зарегестрированы");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User GetUser (String login, String password) {
        User res = null;
        String sql = "select * from mydbtest.user where Name = ? and Password = ?";

        try {
            peparedStatement = con.prepareStatement(sql);
            peparedStatement.setString(1, login);
            peparedStatement.setString(2, password);
            resultSet = peparedStatement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt(1);
                login = resultSet.getString(2);
                password = resultSet.getString(3);
                String NickName = resultSet.getString(4);
                res = new User(id,NickName,login,password);
            }
            else {
                System.out.println("Неверный логин или пароль");
                throw new RuntimeException("Неверный логин или пароль");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка в UserDAO");
        }

        return res;
    }


    public void Close(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        String sql = "select * from mydbtest.user";
        try{
            peparedStatement = con.prepareStatement(sql);
            resultSet = peparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setNickName(resultSet.getString(4));
                userList.add(user);
            }
        } catch (SQLException e){
            System.out.println("Error here");
        }


        return userList;
    }
}
