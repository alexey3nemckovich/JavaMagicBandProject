package main.com.bsuir.autoservice.binding.provider.fakeUOF;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import java.util.HashMap;
import java.util.Map;

public class FakeServiceUOFProvider extends FakeUOFProvider<IServiceUnitOfWork>{
    @Inject
    public FakeServiceUOFProvider(Injector injector) {
        super(injector, IServiceUnitOfWork.class);
    }

    @Override
    protected Map<String, Class[]> getIgnoredMethodNamesAndParameters() {
        return new HashMap<String, Class[]>(){{
            put("getServiceCrudForBean", new Class[]{String.class});
        }};
    }
}
