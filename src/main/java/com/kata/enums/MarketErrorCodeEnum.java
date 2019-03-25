package com.kata.enums;

public enum MarketErrorCodeEnum {
    MRKT_00001("product is null"),
    MRKT_00002("unsatisfied dependency for SimplePriceService");

    private String code;

    MarketErrorCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
