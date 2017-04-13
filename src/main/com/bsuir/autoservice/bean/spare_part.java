package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.Map;

public class spare_part extends Bean{

    public int getSparePartId(){
        return spare_part_id;
    }

    public void setSparePartId(int value){
        this.spare_part_id = value;
    }

    public String getName(){
        return name;
    }

    public void setName(String value){
        this.name = value;
    }

    public int getAmountAvailable(){
        return amount_available;
    }

    public void setAmountAvailable(int value){
        this.amount_available = value;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException{
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("spare_part_id"),
                    type.getDeclaredField("name"),
                    type.getDeclaredField("amount_available")
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
    public spare_part setFields(Map<String, String> fieldValues) {
        spare_part_id = Integer.valueOf(fieldValues.get("spare_part_id"));
        name = fieldValues.get("name");
        amount_available = Integer.valueOf(fieldValues.get("amount_available"));
        return this;
    }

    private int spare_part_id;
    private String name;
    private int amount_available;
}
