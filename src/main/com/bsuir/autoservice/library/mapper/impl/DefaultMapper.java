package main.com.bsuir.autoservice.library.mapper.impl;

import javafx.util.Pair;
import main.com.bsuir.autoservice.library.mapper.IMapper;
import main.com.bsuir.autoservice.library.mapper.binding.IBinding;
import main.com.bsuir.autoservice.library.mapper.exception.MapperException;
import main.com.bsuir.autoservice.library.mapper.binding.factory.IBindingFactory;

import java.lang.reflect.Field;
import java.util.Map;

public class DefaultMapper implements IMapper {
    public DefaultMapper(IBindingFactory bindingFactory){
        this.bindingFactory = bindingFactory;
    }

    private static Object generateNewObject(Class aClass) throws IllegalAccessException, InstantiationException {
        return aClass.newInstance();
    }

    private static Class getFieldType(Object newObject, String parameter) throws NoSuchFieldException {
        Class objectClass = newObject.getClass();
        return objectClass.getField(parameter).getType();
    }


    private Object getMappedObject(Class returnType, Map<String, String[]> parameters, String[] checkedParameters)
            throws InstantiationException, IllegalAccessException {
        Object newObject = generateNewObject(returnType);
        for (String checkedParameter: checkedParameters){
            if (parameters.containsKey(checkedParameter)) {
                updateObject(newObject, checkedParameter, parameters.get(checkedParameter));
            }else{
                //TODO: log unmapped Object
            }
        }
        return newObject;
    }

    private Object getMappedObject(Class returnType, Map<String, String[]> parameters)
            throws InstantiationException, IllegalAccessException {
        Object newObject = generateNewObject(returnType);
        for (Field fieldDeclaration: returnType.getFields()){
            String fieldName = fieldDeclaration.getName();
            if (parameters.containsKey(fieldName)) {
                updateObject(newObject, fieldName, parameters.get(fieldName));
            }else{
                updateDefaultObject(newObject, fieldName);
            }
        }
        return newObject;
    }

    private Object getMappedObject(Class returnType, Map<String, String[]> parameters, Pair<String,String>[] checkedMapParameters)
            throws InstantiationException, IllegalAccessException {
        Object newObject = generateNewObject(returnType);
        for (Pair<String,String> checkedMapParameter: checkedMapParameters){
            if (parameters.containsKey(checkedMapParameter.getKey())) {
                updateObject(newObject, checkedMapParameter.getValue(), parameters.get(checkedMapParameter.getKey()));
            }else{
                //TODO: log unmapped Object
            }
        }
        return newObject;
    }

    private void updateDefaultObject(Object newObject, String fieldName) {
        try {
            Class fieldTypeDeclaration = getFieldType(newObject,fieldName);
            IBinding binding = bindingFactory.getBinding(fieldTypeDeclaration);
            binding.setDefault(newObject,fieldName);
        }catch (Exception e) {
            //TODO: log that invalid parameters
        }
    }

    private void updateObject(Object newObject, String fieldName, String[] currentValues) {
        try {
            Class fieldTypeDeclaration = getFieldType(newObject,fieldName);
            IBinding binding = bindingFactory.getBinding(fieldTypeDeclaration);
            binding.bind(newObject, fieldName, currentValues);
        }catch (Exception e) {
            //TODO: log that invalid parameters
        }
    }

    @Override
    public Object mappedParameters(Class returnType, Map<String, String[]> parameters, String[] checkedParameters)
            throws MapperException {
        try {
            return getMappedObject(returnType,parameters,checkedParameters);
        }catch (Exception e){
            throw new MapperException(e);
        }
    }

    @Override
    public Object mappedParameters(Class returnType, Map<String, String[]> parameters) throws MapperException {
        try {
            return getMappedObject(returnType,parameters);
        }catch (Exception e){
            throw new MapperException(e);
        }
    }

    @Override
    public Object mappedParameters(Class returnType, Map<String, String[]> parameters, Pair<String, String>[] checkedMapParameters)
            throws MapperException {
        try {
            return getMappedObject(returnType,parameters,checkedMapParameters);
        }catch (Exception e){
            throw new MapperException(e);
        }
    }

    IBindingFactory bindingFactory;
}
