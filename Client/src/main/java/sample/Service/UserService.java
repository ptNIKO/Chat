package sample.service;

import sample.dao.UserDAO;
import sample.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(){
        userDAO = new UserDAO();
    }

    public void Close() {
        userDAO.Close();
    }

    public void AddUser(User user){
        // Проверка на пустое поле
            userDAO.AddUser(user);
    }

    public User GetUser(String login,String password){

        return userDAO.GetUser(login,password);
    }

    public List<User> getAll () throws SQLException {
        return userDAO.getAll();
    }

}
