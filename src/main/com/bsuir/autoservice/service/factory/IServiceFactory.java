package main.com.bsuir.autoservice.service.factory;

import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.factory.exception.ServiceFactoryException;

public interface IServiceFactory {
    void addService(String serviceIdentifier, IService service) throws ServiceFactoryException;
    void deleteService(String serviceIdentifier) throws ServiceFactoryException;
    IService getService(String serviceIdentifier) throws ServiceFactoryException;
}
