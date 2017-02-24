package main.com.bsuir.autoservice.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class User extends Bean {
    public enum Type{
        USER,
        STAFF
    }

    private int id;
    private String mail;
    private String login;
    private String password;
    private String phone;
    private String name;
    private String lastName;
    private Type type;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getMail(){
        return  mail;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public Type getType(){
        return type;
    }

    public void setType(Type type){
        this.type = type;
    }

    @Override
    public List<String> getFieldsOrdered(){
        return new ArrayList<String>(Arrays.asList(
                String.valueOf(id), mail, login, password, phone, name, lastName, type.toString()
        ));
    }
}