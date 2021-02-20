package com.pooria.utils;

public class Token implements Comparable {

    private final String value;
    private final Priorities priorities;
    private final Type type;


    public Token(String value, Priorities priorities, Type type) {
        this.value = value;
        this.priorities = priorities;
        this.type = type;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Token)) {
            throw new IllegalArgumentException("You can compare token with token");
        }

        Token token = (Token) o;
        if (token.type != this.type) {
            throw new IllegalArgumentException("You can only compare tokens with same type");
        }

        if (this.type.getValue() < token.type.getValue())
            return 1;
        else if (this.type.getValue() > token.type.getValue())
            return -1;

        return 0;

    }


    public enum Priorities {
        FUNCTION(1), PRODUCT(2), SUM(3), NUMBER(4), PARENTHESES(5);

        private int value;

        Priorities(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    enum Type {
        NUMBER(1), FUNCTION(2);
        private int value;

        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
}
