package com.example.springboot.autoservice.model;

public enum OrderStatus {
    RECEIVED("received"),
    IN_PROCESS("in_process"),
    COMPLETED_SUCCESSFULLY("completed_successfully"),
    NOT_COMPLETED_SUCCESSFULLY("not_completed_successfully"),
    PAID("paid");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
