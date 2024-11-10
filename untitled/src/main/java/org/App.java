package org;
import CustomExceptions.EmailNotRegisteredException;
import CustomExceptions.IncorrectPasswordException;

import User.User;
import User.DefaultUser;
import UserManager.IUserManager;
import UserManager.UserManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main( String[] args ) throws SQLException, IncorrectPasswordException, EmailNotRegisteredException {
        User user = new DefaultUser("Davit","karts","davitqarcivadsze@Gmail.com","1991");
        UserManager.instance.registerUser(user);






    }








}
