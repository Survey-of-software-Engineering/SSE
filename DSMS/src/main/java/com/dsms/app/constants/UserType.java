package com.dsms.app.constants;

public enum UserType {

    GUEST("GUEST"),
    SYS_USER("SYSTEM_USER"),
    ADMIN("ADMIN");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
