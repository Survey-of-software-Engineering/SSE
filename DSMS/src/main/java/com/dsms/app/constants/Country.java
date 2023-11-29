package com.dsms.app.constants;

public enum Country {

    USA("United States of America");

    private String name;

    Country(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }
}
