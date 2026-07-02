package com.hackathon.backend.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users {

    public Users() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Users_id")
    private Integer id;

    @Column(name = "usersname")
    private String usersname;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getusersname() {
        return usersname;
    }

    public void setusersname(String usersname) {
        this.usersname = usersname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}