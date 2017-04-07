package main.com.bsuir.autoservice.http.parser;

import main.com.bsuir.autoservice.http.parser.exception.HttpParserException;
import java.util.Map;

public interface IHttpParser {
    //init all parseObjectType fields with parameter or with default values
    Object parseParameters(Class parseObjectType, Map<String, String[]> parameters)
            throws HttpParserException;
}
