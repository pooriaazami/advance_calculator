package com.pooria.utils.tests;

import com.pooria.utils.Token;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }


    @org.junit.jupiter.api.Test
    void compareTo() {
        Token t1 = new Token("1", Token.Priorities.NUMBER);
        Token t2 = new Token("2", Token.Priorities.NUMBER);
        Token t3 = new Token("+", Token.Priorities.SUM);
        Token t4 = new Token("sin", Token.Priorities.FUNCTION);
        Token t5 = new Token("(", Token.Priorities.PARENTHESES);
        Token t6 = new Token("*", Token.Priorities.PRODUCT);

        assertEquals(t1.compareTo(t2), 0);
        assertEquals(t2.compareTo(t1), 0);
        assertEquals(t3.compareTo(t4), -1);
        assertEquals(t4.compareTo(t3), 1);
        assertEquals(t5.compareTo(t6), -1);
        assertEquals(t6.compareTo(t5), 1);

        assertThrows(IllegalArgumentException.class, () -> t1.compareTo(t3));

        assertThrows(IllegalArgumentException.class, () -> t1.compareTo("Some string"));

        assertThrows(IllegalArgumentException.class, () -> t1.compareTo(null));

    }
}