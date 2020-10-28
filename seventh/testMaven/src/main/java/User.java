public class User {
    private String name;
    private String email;
    private String password;
    private String passwordConf;
    private String checkbox;

    public User(String email, String name, String password, String passwordConf, String checkbox) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordConf = passwordConf;
        this.checkbox = checkbox;
    }

    public User(String email, String name, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConf() {
        return passwordConf;
    }

    public void setPasswordConf(String passwordConf) {
        this.passwordConf = passwordConf;
    }

    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }

    public String[] toRecord(){
        return new String[]{this.email, this.name, this.password};
    }
}
