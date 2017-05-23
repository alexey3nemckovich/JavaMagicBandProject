package main.com.bsuir.autoservice.command.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.AbstractSessionCommand;
import main.com.bsuir.autoservice.command.param.PersonalAccountRestorePassInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountRestorePassRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.infrastructure.session.exception.SessionException;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class PersonalAccountRestorePassCommand extends AbstractSessionCommand<PersonalAccountRestorePassInfo, PersonalAccountRestorePassRet> {

    @Inject
    public PersonalAccountRestorePassCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        super(serviceUnitOfWork, session);
    }

    @Override
    protected PersonalAccountRestorePassRet executeImpl(PersonalAccountRestorePassInfo param) throws Exception {
        return new PersonalAccountRestorePassRet(checkRetryPassword(param));
    }

    private boolean checkRetryPassword(PersonalAccountRestorePassInfo param) throws ServiceException, SessionException {
        boolean isNewPasswordEquals = isEqualPassword(param.getNewPassword(), param.getRetryPassword());

        return isNewPasswordEquals && updatePassword(param.getNewPassword());
    }

    private boolean updatePassword(String newPassword) throws SessionException, ServiceException {
        return serviceUnitOfWork.getUserService().updateUserPassword(session.getUserId(), newPassword);
    }

    private boolean isEqualPassword(String newPass,String retryPass){
        return newPass.equals(retryPass);
    }
}
