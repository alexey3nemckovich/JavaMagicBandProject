package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.Map;

public class order_spare_part extends Bean{

    public int getSparePartId(){
        return spare_part_id;
    }

    public void setSparePartId(int value){
        this.spare_part_id = value;
    }

    public int getOrderId(){
        return order_id;
    }

    public void setOrderId(int value){
        this.order_id = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int value){
        this.count = value;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException{
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("spare_part_id"),
                    type.getDeclaredField("order_id"),
                    type.getDeclaredField("count")
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
    public order_spare_part setFields(Map<String, String> fieldValues) {
        spare_part_id = Integer.valueOf(fieldValues.get("spare_part_id"));
        order_id = Integer.valueOf(fieldValues.get("order_id"));
        count = Integer.valueOf(fieldValues.get("count"));
        return this;
    }

    private int spare_part_id;
    private int order_id;
    private int count;
}
