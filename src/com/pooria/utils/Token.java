package com.pooria.utils;

public class Token {

    enum Priorities {
        FUNCTION, PRODUCT, SUM, PARENTHESES
    }

    enum Type {
        NUMBER, FUNCTION
    }

    public Token(String value, Priorities priorities, Type type) {

    }
}
