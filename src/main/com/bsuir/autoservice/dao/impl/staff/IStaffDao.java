package main.com.bsuir.autoservice.dao.impl.staff;

import main.com.bsuir.autoservice.bean.impl.Staff;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;

public interface IStaffDao extends ICrudDao<Integer, Staff> {
    Staff.Specialization getSpecialization(int idLogin) throws DaoException;
}
