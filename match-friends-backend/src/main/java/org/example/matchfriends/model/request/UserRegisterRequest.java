package org.example.matchfriends.model.request;


import java.io.Serializable;


public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;
    private String userName;

    private String userAccount;

    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }



}
