package sample.dao;

import sample.model.User;

import java.sql.*;

public class LobbyDAO {

    private Connection con = null;
    private PreparedStatement peparedStatement = null;
    private int result;
    private ResultSet resultSet;

    public LobbyDAO (){
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

    public void AddLobby (final String name, final User user){
        String sql = "insert into mydbtest.lobby (Nme,idUser) values (?,?)";
        try {
            peparedStatement = con.prepareStatement(sql);
            peparedStatement.setString(1,name);
            peparedStatement.setInt(2,user.getId());
            result = peparedStatement.executeUpdate();
            if (result == 1)
                System.out.println("Лобби созданно успешно");
            else
                System.out.println("Возникла ошибка при создании лобби");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void Close(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
