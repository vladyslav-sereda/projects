package ua.nure.sereda.Photostudio.models;

import java.io.Serializable;

/**
 * Created by sered on 10.05.2017.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 4986974738835662394L;
    private int id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private String password;

    public User() {
    }

    public User(String email, String name, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}
