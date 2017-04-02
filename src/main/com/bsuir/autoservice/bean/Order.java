package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Order extends Bean{
    public enum State{
        QUEUED, EXECUTING, DONE, REJECTED
    }

    private int id;
    private int userId;
    private int serviceShopId;
    private Date dateOpen;
    private Date dateClose;
    private int sum;
    private State state;

    public int getId() {
        return id;
    }

    public void setId(int value){
        this.id = value;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int value){
        this.userId = value;
    }

    public int getServiceShopId(){
        return serviceShopId;
    }

    public void setServiceShopId(int value){
        this.serviceShopId = value;
    }

    public Date getDateOpen(){
        return dateOpen;
    }

    public void setDateOpen(Date value){
        this.dateOpen = value;
    }

    public Date getDateClose(){
        return dateClose;
    }

    public void setDateClose(Date value){
        this.dateClose = value;
    }

    public int getSum(){
        return sum;
    }

    public void setSum(int value){
        this.sum = value;
    }

    public State getState(){
        return state;
    }

    public void setState(State value){
        this.state = value;
    }

    @Override
    public List<Field> getFieldsOrdered(){
        return null;
    }
}