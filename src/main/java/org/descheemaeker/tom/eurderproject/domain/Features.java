package org.descheemaeker.tom.eurderproject.domain;

import java.util.List;

public enum Features {
    ADD_NEW_ITEM(List.of(UserType.ADMIN));

    private final List<UserType> permittedUsers;

    Features(List<UserType> permittedUsers) {
        this.permittedUsers = permittedUsers;
    }

    public List<UserType> getPermittedUsers() {
        return permittedUsers;
    }

    public boolean isPermitted(UserType userType) {
        return this.permittedUsers.contains(userType);
    }
}
