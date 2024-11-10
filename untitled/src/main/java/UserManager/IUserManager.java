package UserManager;

import CustomExceptions.EmailNotRegisteredException;
import CustomExceptions.IncorrectPasswordException;
import User.User;

import java.sql.SQLException;

public interface IUserManager {
    void registerUser(User user) throws SQLException;
    User login(String email, String password) throws SQLException,EmailNotRegisteredException, IncorrectPasswordException;
    boolean checkEmail(String email) throws SQLException;

}
