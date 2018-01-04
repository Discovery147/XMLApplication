package com.sizonenko.xmlapp.parsing;

public enum RequestEnum {
    TOURIST_VOUCHERS("tourist-vouchers"),
    REQUEST_ID("request-id"),
    TYPE("type"),
    VOUCHER("voucher"),
    COUNTRY("country"),
    DAYS("days"),
    NIGHTS("nights"),
    TRANSPORT("transport"),
    COST("cost"),
    INCLUDED("included"),
    STARS("stars"),
    FOOD("food"),
    ROOM("room"),
    CONDITIONER("conditioner"),
    TV("tv"),
    GYM("gym"),
    AVERAGE_COST("average-cost"),
    INFO("info"),
    HOTEL("hotel");

    private String value;

    private RequestEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
