package sample;

public class User {
    private String nick;
    private String login;
    private String password;

    public User(String nick, String login, String password){
        this.nick = nick;
        this.login = login;
        this.password = password;
    }


    public String getNick() {
        return nick;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
