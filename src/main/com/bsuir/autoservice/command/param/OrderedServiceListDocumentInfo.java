package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.RequestParameter;

import java.util.HashMap;
import java.util.Map;

public class OrderedServiceListDocumentInfo extends DocumentInfo{

    @RequestParameter
    public int userId;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        Map<String, String[]> mParams = new HashMap<>(
                super.parse(params)
        );

        format = mParams.get("userId").toString();
        mParams.remove("userId");

        return mParams;
    }
}
