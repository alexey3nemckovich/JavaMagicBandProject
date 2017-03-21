package main.com.bsuir.autoservice.service.factory.impl;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.factory.IServiceFactory;
import main.com.bsuir.autoservice.service.factory.exception.ServiceFactoryException;

import java.util.HashMap;
import java.util.Map;

public class DefaultServiceFactory implements IServiceFactory {

    public DefaultServiceFactory(){
        serviceMap = new HashMap<>();
    }

    @Override
    public void addService(String serviceIdentifier, IService service) throws ServiceFactoryException {
        try {
            serviceMap.put(serviceIdentifier,service);
        } catch (Exception e) {
            throw new ServiceFactoryException(e);
        }
    }

    @Override
    public void deleteService(String serviceIdentifier) throws ServiceFactoryException {
        try {
            if (serviceMap.containsKey(serviceIdentifier)) {
                serviceMap.remove(serviceIdentifier);
            }else {
                //TODO: log that not having key
            }
        }
        catch (Exception e){
            throw new ServiceFactoryException(e);
        }
    }

    @Override
    public IService getService(String serviceIdentifier) throws ServiceFactoryException {
        try {
            return serviceMap.get(serviceIdentifier);
        }catch (Exception e){
            throw new ServiceFactoryException(e);
        }
    }

    private final Map<String,IService> serviceMap;
}
