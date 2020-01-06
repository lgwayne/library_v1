package com.library.bean;


/**
 * 管理员实体类
 */
public class Admin {

    private long admin_id;
    private String password;
    private String username;

    public long getAdminId() {
        return admin_id;
    }

    public void setAdminId(long admin_id) {
        this.admin_id = admin_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
