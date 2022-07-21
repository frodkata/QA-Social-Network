package com.restAPI.QA.Entities;

public enum Role {
    ADMIN,USER;

    @Override
    public String toString() {
        return  "ROLE_"+this.name();
    }
}
