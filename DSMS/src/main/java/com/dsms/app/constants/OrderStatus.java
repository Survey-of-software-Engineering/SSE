package com.dsms.app.constants;

import java.util.Map;
import java.util.TreeMap;

public enum OrderStatus {

    PLACED("Placed"),
    IN_PROGRESS("Progress"),
    READY_FOR_DELIVERY("Out_For_Delivery"),
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

    public static Map<OrderStatus, String> getStatusSkip(OrderStatus curr) {
        Map<OrderStatus, String> statuses = new TreeMap<>();
        boolean s = false;
        for (OrderStatus status : OrderStatus.values()) {
            if(status == curr) {
                s = true;
                continue;
            }
            if(s) {
                statuses.put(status, status.getStatusText());
            }
        }
        return statuses;
    }

    public static OrderStatus getStatusByText(String enumParam) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getStatusText().equals(enumParam)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Enum type not found for parameter: " + enumParam);
    }
}
