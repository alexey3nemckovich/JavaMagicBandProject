package main.com.bsuir.autoservice.library.mapper.impl;

import com.google.inject.Inject;
import javafx.util.Pair;
import main.com.bsuir.autoservice.library.mapper.IMapper;
import main.com.bsuir.autoservice.library.mapper.binding.IBinding;
import main.com.bsuir.autoservice.library.mapper.binding.factory.IBindingFactory;
import main.com.bsuir.autoservice.library.mapper.exception.MapperException;

import java.lang.reflect.Field;
import java.util.Map;

public class DefaultMapper implements IMapper {
    private final IBindingFactory bindingFactory;

    @Inject
    public DefaultMapper(IBindingFactory bindingFactory){
        this.bindingFactory = bindingFactory;
    }

    private static <T> T generateNewObject(Class<T> aClass) throws IllegalAccessException, InstantiationException {
        return aClass.newInstance();
    }

    private static <T> Class getFieldType(T newObject, String parameter) throws NoSuchFieldException {
        Class objectClass = newObject.getClass();
        return objectClass.getField(parameter).getType();
    }


    private <T> T getMappedObject(Class<T> returnType, Map<String, String[]> parameters, String[] checkedParameters)
            throws InstantiationException, IllegalAccessException {
        T newObject = generateNewObject(returnType);
        for (String checkedParameter: checkedParameters){
            if (parameters.containsKey(checkedParameter)) {
                updateObject(newObject, checkedParameter, parameters.get(checkedParameter));
            }else{
                //TODO: log unmapped Object
            }
        }
        return newObject;
    }

    private <T> T getMappedObject(Class<T> returnType, Map<String, String[]> parameters)
            throws InstantiationException, IllegalAccessException {
        T newObject = generateNewObject(returnType);
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

    private <T> T getMappedObject(Class<T> returnType, Map<String, String[]> parameters, Pair<String,String>[] checkedMapParameters)
            throws InstantiationException, IllegalAccessException {
        T newObject = generateNewObject(returnType);
        for (Pair<String,String> checkedMapParameter: checkedMapParameters){
            if (parameters.containsKey(checkedMapParameter.getKey())) {
                updateObject(newObject, checkedMapParameter.getValue(), parameters.get(checkedMapParameter.getKey()));
            }else{
                //TODO: log unmapped Object
            }
        }
        return newObject;
    }

    private <T> void updateDefaultObject(T newObject, String fieldName) {
        try {
            Class fieldTypeDeclaration = getFieldType(newObject,fieldName);
            IBinding binding = bindingFactory.getBinding(fieldTypeDeclaration);
            binding.setDefault(newObject,fieldName);
        }catch (Exception e) {
            //TODO: log that invalid parameters
        }
    }

    private <T> void updateObject(T newObject, String fieldName, String[] currentValues) {
        try {
            Class fieldTypeDeclaration = getFieldType(newObject,fieldName);
            IBinding binding = bindingFactory.getBinding(fieldTypeDeclaration);
            binding.bind(newObject, fieldName, currentValues);
        }catch (Exception e) {
            //TODO: log that invalid parameters
        }
    }

    @Override
    public <T> T mappedParameters(Class<T> returnType, Map<String, String[]> parameters, String[] checkedParameters)
            throws MapperException {
        try {
            return getMappedObject(returnType,parameters,checkedParameters);
        }catch (Exception e){
            throw new MapperException(e);
        }
    }

    @Override
    public <T> T mappedParameters(Class<T> returnType, Map<String, String[]> parameters) throws MapperException {
        try {
            return getMappedObject(returnType,parameters);
        }catch (Exception e){
            throw new MapperException(e);
        }
    }

    @Override
    public <T> T mappedParameters(Class<T> returnType, Map<String, String[]> parameters, Pair<String, String>[] checkedMapParameters)
            throws MapperException {
        try {
            return getMappedObject(returnType,parameters,checkedMapParameters);
        }catch (Exception e){
            throw new MapperException(e);
        }
    }
}
