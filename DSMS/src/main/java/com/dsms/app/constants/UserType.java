package com.dsms.app.constants;

public enum UserType {

    GUEST("guest"),
    USER("user"),
    ADMIN("admin");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}