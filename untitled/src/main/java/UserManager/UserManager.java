package UserManager;

import CustomExceptions.EmailNotRegisteredException;
import CustomExceptions.IncorrectPasswordException;
import CustomExceptions.InvalidEmailException;
import User.DefaultUser;
import User.User;
import database.DBConnect;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserManager implements IUserManager {
    public static UserManager instance = new UserManager();
    private static final Connection conn = DBConnect.connect();
    private static final Argon2 argon2 = Argon2Factory.create();
    private UserManager() {};
    @Override
    public  void registerUser(User user) throws SQLException {
        if(checkEmail(user.getEmail()))
            throw new InvalidEmailException("invalid email address");
        else {
            String query = "INSERT INTO Users(first_name,last_name,email,password)" +
                           "VALUES(?,?,?,?)";
            try( PreparedStatement ps = conn.prepareStatement(query)) {

                String hashedPassword = argon2.hash(2, 65536, 1, user.getPassword().toCharArray());
                ps.setString(1, user.getFirst_name());
                ps.setString(2, user.getLast_name());
                ps.setString(3, user.getEmail());
                ps.setString(4, hashedPassword);
                ps.executeUpdate();
                System.out.println("User registered successfully");
            }
        }
    }

    @Override
    public User login(String email, String password) throws SQLException, EmailNotRegisteredException,IncorrectPasswordException {
        if (!checkEmail(email))
            throw new EmailNotRegisteredException("Email not registered");
        else {
            String query = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String dbPassword = rs.getString("password");
                        if (!argon2.verify(dbPassword, password.toCharArray()))
                            throw new IncorrectPasswordException("Incorrect Password");
                        return new DefaultUser(
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email"),
                                dbPassword
                        );
                    }
                }
            }
        }
        throw new SQLException("Login method need modification");
    }
    @Override
    public boolean checkEmail(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM Users WHERE email = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(ps);
                    return rs.getInt(1) > 0;
                }
                return false;
            }
        }
    }




}
