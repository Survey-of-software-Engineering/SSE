package com.dsms.app.constants;

import java.util.Map;
import java.util.TreeMap;

public enum Country {

    USA("USA");

    private String name;

    Country(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    public static Map<Country, String> getCountries() {
        Map<Country, String> countries = new TreeMap<>();
        for (Country country : Country.values()) {
            countries.put(country, country.getName());
        }
        return countries;
    }
}
