package sample.Model;

public class User {

    private String nickName;
    private String login;
    private String password;


    public User() {
    }

    public User(String nickName, String login, String password) {
        this.nickName = nickName;
        this.login = login;
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
