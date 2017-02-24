package main.com.bsuir.autoservice.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderedService extends Bean{
    private int serviceId;
    private int orderId;
    private Date date;

    public int getServiceId(){
        return serviceId;
    }

    public void setServiceId(int value){
        this.serviceId = value;
    }

    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int value){
        this.orderId = value;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date value){
        this.date = value;
    }

    @Override
    public List<String> getFieldsOrdered(){
        return new ArrayList<String>(Arrays.asList(
                String.valueOf(serviceId), String.valueOf(orderId), date.toString()
        ));
    }
}