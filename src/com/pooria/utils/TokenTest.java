package com.pooria.utils;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }


    @org.junit.jupiter.api.Test
    void compareTo() {
        Token t1 = new Token("1", Token.Priorities.NUMBER);
        Token t2 = new Token("*", Token.Priorities.PRODUCT);

    }
}