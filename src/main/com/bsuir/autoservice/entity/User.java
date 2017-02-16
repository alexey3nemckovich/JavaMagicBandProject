package main.com.bsuir.autoservice.entity;

import java.util.LinkedList;
import java.util.List;

public class User implements Entity{
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

    public List<String> getFields(){
        List<String> fields = new LinkedList<String>();
        fields.add(String.valueOf(id));
        fields.add(mail);
        fields.add(login);
        fields.add(password);
        fields.add(phone);
        fields.add(name);
        fields.add(lastName);
        fields.add(type.toString());
        return fields;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id + "|");
        stringBuilder.append(mail + "|");
        stringBuilder.append(login + "|");
        stringBuilder.append(password + "|");
        stringBuilder.append(phone + "|");
        stringBuilder.append(name + "|");
        stringBuilder.append(lastName + "|");
        stringBuilder.append(type);
        return stringBuilder.toString();
    }
}