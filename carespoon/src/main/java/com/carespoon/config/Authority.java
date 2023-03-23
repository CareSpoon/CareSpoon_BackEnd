package com.carespoon.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Authority {
    SENIOR("ROLE_SENIOR"),
    VIEWER("ROLE_VIEWER");

    private String value;
}
