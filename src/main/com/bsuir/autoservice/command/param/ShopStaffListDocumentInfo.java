package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.RequestParameter;

import java.util.HashMap;
import java.util.Map;

public class ShopStaffListDocumentInfo extends DocumentInfo{

    @RequestParameter
    public int shopId;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        Map<String, String[]> mParams = new HashMap<>(
                super.parse(params)
        );

        shopId = Integer.valueOf(mParams.get("shopId")[0]);
        mParams.remove("shopId");

        return mParams;
    }
}
