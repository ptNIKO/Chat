package sample.model;

public class User {

    private String nickName;
    private String login;
    private String password;
    private int id;


    public User() {
    }

    public User(int id,String nickName, String login, String password) {
        this.id = id;
        this.nickName = nickName;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
