package sample.dao;

import sample.model.User;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    List<User> getAll() throws SQLException;
    //TODO допилить интерфейс
}
