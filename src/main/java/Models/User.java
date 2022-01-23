package Models;

public class User {
    /**
     * User class
     */
    private String pin;
    private String login;
    public User(String pin,String login){
        this.login=login;
        this.pin=pin;
    }

    public String getLogin() {
        return login;
    }

    public String getPin() {
        return pin;
    }
}
