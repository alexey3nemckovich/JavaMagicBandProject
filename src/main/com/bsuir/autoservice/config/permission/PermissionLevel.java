package main.com.bsuir.autoservice.config.permission;

import main.com.bsuir.autoservice.bean.impl.Staff;
import main.com.bsuir.autoservice.library.DefaultHashMap;

import java.util.Map;

public enum PermissionLevel {
    GUEST, USER, MECHANIC, CHIEF_MECHANIC, ADMINISTRATOR, DIRECTOR;

    private static Map<Staff.Specialization, PermissionLevel> specializationPermissionLevelMap;

    static {
        specializationPermissionLevelMap = new DefaultHashMap<Staff.Specialization, PermissionLevel>(USER){{
            put(Staff.Specialization.ADMIN, ADMINISTRATOR);
            put(Staff.Specialization.CHIEF_MECHANIC, CHIEF_MECHANIC);
            put(Staff.Specialization.DIRECTOR, DIRECTOR);
            put(Staff.Specialization.MECHANIC, MECHANIC);
        }};
    }

    public static PermissionLevel convertStaffPermission(Staff.Specialization staffSpecialization) {
        return specializationPermissionLevelMap.get(staffSpecialization);
    }
}
