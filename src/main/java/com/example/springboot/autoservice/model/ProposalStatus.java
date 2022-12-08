package com.example.springboot.autoservice.model;

public enum ProposalStatus {
    PAID("paid"),
    NOT_PAID("not_paid");

    private String value;

    ProposalStatus(String value) {
        this.value = value;
    }
}
