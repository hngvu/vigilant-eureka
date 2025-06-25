package com.orchidservice.exception;


import lombok.Getter;

@Getter
public enum ErrorCode {
    LOGIN_FAIL(400, "Incorrect username or password"),
    NOT_FOUND_ACCOUNT(400, "Account not found");


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code; // Assigning code based on ordinal
        this.message = message;
    }
}
