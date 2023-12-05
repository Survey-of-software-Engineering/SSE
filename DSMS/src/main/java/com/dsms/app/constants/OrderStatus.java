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

    public String getStatusText() {
        return this.status;
    }

    public static Map<OrderStatus, String> getStatus() {
        Map<OrderStatus, String> statuses = new TreeMap<>();
        for (OrderStatus status : OrderStatus.values()) {
            statuses.put(status, status.getStatusText());
        }
        return statuses;
    }
}
