package main.com.bsuir.autoservice.dto;

import main.com.bsuir.autoservice.bean.impl.Staff;

import java.util.Objects;

public class UserStaffDTO {
    private int userId;
    private String userName;
    private Staff.Specialization staffLevel;

    public UserStaffDTO(int userId, String userName, Staff.Specialization staffLevel) {
        this.userId = userId;
        this.userName = userName;
        this.staffLevel = staffLevel;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Staff.Specialization getStaffLevel() {
        return staffLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStaffDTO that = (UserStaffDTO) o;
        return userId == that.userId &&
                Objects.equals(userName, that.userName) &&
                staffLevel == that.staffLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, staffLevel);
    }
}
