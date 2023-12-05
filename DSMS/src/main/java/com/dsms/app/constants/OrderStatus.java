package com.dsms.app.constants;

import java.util.Map;
import java.util.TreeMap;

public enum OrderStatus {

    PLACED("Placed"),
    IN_PROGRESS("Progress"),
    READY_FOR_DELIVERY("Out for Delivery"),
    SUCCESS("Success");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public static Map<Country, String> getStatus() {
        Map<Country, String> countries = new TreeMap<>();
        for (Country country : Country.values()) {
            countries.put(country, country.getName());
        }
        return countries;
    }
}
