package org.example.matchfriends.model.request;


import java.io.Serializable;


public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;

    private String username;

    private String userAccount;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
