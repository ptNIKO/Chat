package sample.Service;

import sample.DAO.UserDAO;
import sample.Model.User;

public class UserService {
    private UserDAO userDAO;

    public UserService(){
        userDAO = new UserDAO();
    }

    public void Close()
    {
        userDAO.Close();
    }

    public void AddUser(User user){
        // Проверка на пустое поле
            userDAO.AddUser(user);
    }
    public User GetUser(String login,String password){

        return userDAO.GetUser(login,password);
    }



}
