package com.sparta.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    CUSTOMER("CUSTOMER"),
    OWNER("OWNER"),
    MASTER("MASTER");

    private String authority;
}
