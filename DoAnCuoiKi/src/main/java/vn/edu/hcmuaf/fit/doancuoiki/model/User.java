package vn.edu.hcmuaf.fit.doancuoiki.model;

import javax.management.relation.Role;

public class User {
    private int id;
    private String email;
    private String password;
    private UserInfo userInfo;
    private Role role;
    private boolean isActive;

    public User() {
    }

    public User(int id, String email, String password, UserInfo userInfo, Role role, boolean isActive) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userInfo = userInfo;
        this.role = role;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
