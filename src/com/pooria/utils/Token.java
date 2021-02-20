package com.pooria.utils;

public class Token implements Comparable {

    private final String value;
    private final Priorities priorities;
    private final Type type;

    public Token(String value, Priorities priorities) {
        this.value = value;
        this.priorities = priorities;

        if (priorities == Priorities.NUMBER)
            this.type = Type.NUMBER;
        else
            this.type = Type.FUNCTION;
    }

    public String getValue() {
        return value;
    }

    public Priorities getPriorities() {
        return priorities;
    }

    public Type getType() {
        return type;
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

        if (this.getPriorities().getValue() < token.getPriorities().getValue())
            return 1;
        else if (this.getPriorities().getValue() > token.getPriorities().getValue())
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
            return this.value;
        }
    }

    enum Type {
        NUMBER(1), FUNCTION(2);
        private int value;

        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

    }
}
