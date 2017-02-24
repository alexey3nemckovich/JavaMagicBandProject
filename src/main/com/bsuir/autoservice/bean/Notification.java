package main.com.bsuir.autoservice.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Notification extends Bean{
    public enum State{
        CONFIRMED, UNCONFIRMED
    }

    private int id;
    private int orderId;
    private int staffId;
    private Date date;
    private String content;
    private State state;

    public int getId(){
        return id;
    }

    public void setId(int value){
        this.id = value;
    }

    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int value){
        this.orderId = value;
    }

    public int getStaffId(){
        return staffId;
    }

    public void setStaffId(int value){
        this.staffId = value;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date value){
        this.date = value;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String value){
        this.content = value;
    }

    public State getState(){
        return state;
    }

    public void setState(State value){
        this.state = value;
    }

    @Override
    public List<String> getFieldsOrdered(){
        return new ArrayList<String>(Arrays.asList(
                String.valueOf(id), String.valueOf(orderId), String.valueOf(staffId), date.toString(), content, state.toString()
        ));
    }
}