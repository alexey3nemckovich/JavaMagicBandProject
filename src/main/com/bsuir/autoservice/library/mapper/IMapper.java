package main.com.bsuir.autoservice.library.mapper;

import javafx.util.Pair;
import main.com.bsuir.autoservice.library.mapper.exception.MapperException;

import java.util.Map;

public interface IMapper {
    //map only checkedParameters with simple name
    <T> T mappedParameters(Class<T> returnType, Map<String, String[]> parameters, String[] checkedParameters) throws MapperException;
    //map all returnType Field or set Default
    <T> T mappedParameters(Class<T> returnType, Map<String, String[]> parameters) throws MapperException;
    //map only checkedParameters by pair : parameters value - returnType field
    <T> T mappedParameters(Class<T> returnType, Map<String, String[]> parameters, Pair<String,String>[] checkedMapParameters) throws MapperException;
}
