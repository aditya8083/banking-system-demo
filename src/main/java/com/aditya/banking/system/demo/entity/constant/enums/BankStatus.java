package com.aditya.banking.system.demo.entity.constant.enums;

public enum BankStatus {

    OPEN("open"), CLOSED("closed");

    private String status;

    BankStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
