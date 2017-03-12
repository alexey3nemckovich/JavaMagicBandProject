package main.com.bsuir.autoservice.library.mapper;

import javafx.util.Pair;
import main.com.bsuir.autoservice.library.mapper.exception.MapperException;

import java.util.Map;

public interface IMapper {
    //map only checkedParameters with simple name
    Object mappedParameters(Class returnType, Map<String, String[]> parameters, String[] checkedParameters) throws MapperException;
    //map all returnType Field or set Default
    Object mappedParameters(Class returnType, Map<String, String[]> parameters) throws MapperException;
    //map only checkedParameters by pair : parameters value - returnType field
    Object mappedParameters(Class returnType, Map<String, String[]> parameters, Pair<String,String>[] checkedMapParameters) throws MapperException;
}
