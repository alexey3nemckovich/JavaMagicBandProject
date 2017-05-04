package main.com.bsuir.autoservice.config.permission;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Permission {
    private static final PermissionLevel DEFAULT_PERMISSION_LEVEL = PermissionLevel.USER;
    private static final PermissionAccessType DEFAULT_ACCCESS_TYPE = PermissionAccessType.EQUALLY_OR_HIGHER;

    private final List<PermissionLevel> level;
    //if lower, choice all levels + levels, less than the smallest level
    //if higher, choice all levels + levels, more than the highest level
    private final PermissionAccessType access;

    public Permission(List<PermissionLevel> level, PermissionAccessType access){
        this.level = level;
        this.access = access;
    }

    public List<PermissionLevel> getPermissionLevel() {
        return level;
    }

    public PermissionAccessType getAccess() {
        return access;
    }

    public static class Builder{
        private List<PermissionLevel> nestedLevel = Collections.singletonList(DEFAULT_PERMISSION_LEVEL);
        private PermissionAccessType nestedAccess = DEFAULT_ACCCESS_TYPE;

        public Builder setNestedPermissionLevel(PermissionLevel[] nestedLevels) {
            this.nestedLevel = Arrays.asList(nestedLevels);

            return this;
        }

        public Builder setNestedPermissionLevel(PermissionLevel nestedLevel) {
            this.nestedLevel = Collections.singletonList(nestedLevel);

            return this;
        }

        public Builder setNestedAccess(PermissionAccessType nestedAccess) {
            this.nestedAccess = nestedAccess;

            return this;
        }

        public Permission build(){
            return new Permission(nestedLevel, nestedAccess);
        }
    }
}
