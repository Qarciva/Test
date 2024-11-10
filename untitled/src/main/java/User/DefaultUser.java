package User;

public class DefaultUser implements User {
   private String first_name;
   private String last_name;
   private String email;
   private String password;


    public DefaultUser(String firstName, String lastName, String email, String password) {
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.password = password;
    }
    @Override
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getFirst_name() {
        return first_name;
    }

    @Override
    public String getLast_name() {
        return last_name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
