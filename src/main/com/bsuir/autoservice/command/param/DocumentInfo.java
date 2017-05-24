package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.command.RequestParameter;

import java.util.HashMap;
import java.util.Map;

public class DocumentInfo implements ICommandParam{

    @RequestParameter
    public String format;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        Map<String, String[]> mParams = new HashMap<>(params);

        format = mParams.get("format")[0].toString();
        mParams.remove("format");

        return mParams;
    }
}
