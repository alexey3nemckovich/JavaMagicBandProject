package main.com.bsuir.autoservice.service.crud.impl.report;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.report;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class ReportService extends AbstractServiceCrud<Integer, report> implements IReportService {

    @Inject
    public ReportService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getReportDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
