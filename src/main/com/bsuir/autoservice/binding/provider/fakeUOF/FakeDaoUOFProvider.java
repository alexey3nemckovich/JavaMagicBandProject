package main.com.bsuir.autoservice.binding.provider.fakeUOF;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;

public class FakeDaoUOFProvider extends FakeUOFProvider<IDaoUnitOfWork> {
    @Inject
    public FakeDaoUOFProvider(Injector injector) {
        super(injector, IDaoUnitOfWork.class);
    }
}
