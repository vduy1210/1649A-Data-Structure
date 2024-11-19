package models;

public class User {
    private String username;
    private String address;

    public User(String username, String address) {
        this.username = username;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Address: " + address;
    }
}