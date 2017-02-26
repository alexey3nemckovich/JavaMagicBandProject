package main.com.bsuir.autoservice.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShareDiscount extends Bean{
    private int shareId;
    private int discountId;

    public int getShareId(){
        return shareId;
    }

    public void setShareId(int value){
        this.shareId = value;
    }

    public int getDiscountId(){
        return discountId;
    }

    public void setDiscountId(int value){
        this.discountId = value;
    }

    @Override
    public List<String> getFieldsOrdered(){
        return new ArrayList<String>(Arrays.asList(
                String.valueOf(shareId), String.valueOf(discountId)
        ));
    }
}