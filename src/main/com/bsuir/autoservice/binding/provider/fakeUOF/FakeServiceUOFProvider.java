package main.com.bsuir.autoservice.binding.provider.fakeUOF;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class FakeServiceUOFProvider extends FakeUOFProvider<IServiceUnitOfWork>{
    @Inject
    public FakeServiceUOFProvider(Injector injector) {
        super(injector, IServiceUnitOfWork.class);
    }
}
