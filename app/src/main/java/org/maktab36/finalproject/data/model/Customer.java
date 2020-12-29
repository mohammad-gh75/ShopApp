package org.maktab36.finalproject.data.model;

public class Customer {
    private int mId;
    private String mUsername;
    private String mEmail;
    private String mRole;

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        mRole = role;
    }

    public Customer() {
    }

    public Customer(int id, String username, String email, String role) {
        mId = id;
        mUsername = username;
        mEmail = email;
        mRole = role;
    }
}
