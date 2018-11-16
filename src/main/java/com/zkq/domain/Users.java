package com.zkq.domain;

import org.springframework.stereotype.Component;
/**
 * @author zkq15
 * */

@Component
public class Users {
    private Integer id;
     private String username;
    private String password;
   private String addres;
    public Users(){}
    public Users(Integer id,String username,String password,String addres){
        this.username=username;
        this.id=id;
        this.password=password;
        this.addres=addres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }
}