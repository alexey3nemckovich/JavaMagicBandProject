package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.*;

public class user extends Bean {
    public enum Type{
        USER,
        STAFF;
    }

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

    public String getLast_name(){
        return last_name;
    }

    public void setLastName(String last_name){
        this.last_name = last_name;
    }

    public Type getType(){
        return type;
    }

    public void setType(Type type){
        this.type = type;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException{
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("id"),
                    type.getDeclaredField("mail"),
                    type.getDeclaredField("login"),
                    type.getDeclaredField("password"),
                    type.getDeclaredField("phone"),
                    type.getDeclaredField("name"),
                    type.getDeclaredField("last_name"),
                    type.getDeclaredField("type")
            };
            for (Field field: fields) {
                field.setAccessible(true);
            }
            return fields;
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    @Override
    public user setFields(Map<String, String> fieldValues) {
        id = Integer.valueOf(fieldValues.get("id"));
        mail = fieldValues.get("mail");
        login = fieldValues.get("login");
        password = fieldValues.get("password");
        phone = fieldValues.get("phone");
        name = fieldValues.get("name");
        last_name = fieldValues.get("last_name");
        type = Type.valueOf(fieldValues.get("type"));
        return this;
    }

    private int id;
    private String mail;
    private String login;
    private String password;
    private String phone;
    private String name;
    private String last_name;
    private Type type;
}
