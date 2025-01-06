package vn.edu.hcmuaf.fit.doancuoiki.model;

public class User {
    private int id;
    private String email;
    private String password;
    private UserInfo userInfo;
    private int roleId;
    private boolean isActive;

    public User() {
    }

    public User(String email, String password, UserInfo userInfo, boolean isActive) {
        this.email = email;
        this.password = password;
        this.userInfo = userInfo;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
