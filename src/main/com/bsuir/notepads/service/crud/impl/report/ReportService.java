package main.com.bsuir.notepads.service.crud.impl.report;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.report;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class ReportService extends AbstractServiceCrud<Integer, report> implements IReportService {

    @Inject
    public ReportService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getReportDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
