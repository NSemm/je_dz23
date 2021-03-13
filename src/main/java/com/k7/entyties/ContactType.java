package com.k7.entyties;

public enum ContactType {
    PHONE(1), EMAIL(2);
    int type;

    ContactType(int type) {
        this.type = type;
    }
}
