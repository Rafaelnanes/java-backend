package rbn.edu.jwt;

public class AccountCredentials {

    private String login;
    private String password;

    public String getPassword() {
	return password;
    }

    public void setPassword(String _password) {
	this.password = _password;
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }
}
