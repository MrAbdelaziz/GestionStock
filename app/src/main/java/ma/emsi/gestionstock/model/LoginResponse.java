package ma.emsi.gestionstock.model;

import java.util.List;

public class LoginResponse {

    private String username,password;

    public LoginResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
