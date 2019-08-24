package sample.DAO;

import sample.Model.User;

import java.sql.*;

public class UserDAO {
    // Get and Add user
    private Connection con = null;
    private PreparedStatement peparedStatement = null;
    private int result;
    private ResultSet resultSet;

    public  UserDAO (){
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
//query
        String sql = "select * from mydbtest.user where Name = ? and Password = ?";

        try {
            peparedStatement = con.prepareStatement(sql);
            peparedStatement.setString(1, login);
            peparedStatement.setString(2, password);
            resultSet = peparedStatement.executeQuery();
            if (resultSet.next()){
                login = resultSet.getString(2);
                password = resultSet.getString(3);
                String NickName = resultSet.getString(4);
                res = new User(NickName,login,password);
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
        try
        {
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
