package com.dsms.app.constants;

import java.util.Map;
import java.util.TreeMap;

public enum State {
    ALABAMA("Alabama"),
    ALASKA("Alaska"),
    ARIZONA("Arizona"),
    ARKANSAS("Arkansas"),
    CALIFORNIA("California"),
    COLORADO("Colorado"),
    CONNECTICUT("Connecticut"),
    DELAWARE("Delaware"),
    FLORIDA("Florida"),
    GEORGIA("Georgia"),
    HAWAII("Hawaii"),
    IDAHO("Idaho"),
    ILLINOIS("Illinois"),
    INDIANA("Indiana"),
    IOWA("Iowa"),
    KANSAS("Kansas"),
    KENTUCKY("Kentucky"),
    LOUISIANA("Louisiana"),
    MAINE("Maine"),
    MARYLAND("Maryland"),
    MASSACHUSETTS("Massachusetts"),
    MICHIGAN("Michigan"),
    MINNESOTA("Minnesota"),
    MISSISSIPPI("Mississippi"),
    MISSOURI("Missouri"),
    MONTANA("Montana"),
    NEBRASKA("Nebraska"),
    NEVADA("Nevada"),
    NEW_HAMPSHIRE("New Hampshire"),
    NEW_JERSEY("New Jersey"),
    NEW_MEXICO("New Mexico"),
    NEW_YORK("New York"),
    NORTH_CAROLINA("North Carolina"),
    NORTH_DAKOTA("North Dakota"),
    OHIO("Ohio"),
    OKLAHOMA("Oklahoma"),
    OREGON("Oregon"),
    PENNSYLVANIA("Pennsylvania"),
    RHODE_ISLAND("Rhode Island"),
    SOUTH_CAROLINA("South Carolina"),
    SOUTH_DAKOTA("South Dakota"),
    TENNESSEE("Tennessee"),
    TEXAS("Texas"),
    UTAH("Utah"),
    VERMONT("Vermont"),
    VIRGINIA("Virginia"),
    WASHINGTON("Washington"),
    WEST_VIRGINIA("West Virginia"),
    WISCONSIN("Wisconsin"),
    WYOMING("Wyoming");

    private final String name;

    State(String fullName) {
        this.name = fullName;
    }

    public String getName() {
        return name;
    }

    public static Map<State, String> getStates() {
        Map<State, String> states = new TreeMap<>();
        for (State state : State.values()) {
            states.put(state, state.getName());
        }
        return states;
    }
}
