package com.pooria.utils.tests;

import com.pooria.utils.Token;
import com.pooria.utils.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    private String expression1 = "12.5+5*13.67-23\\99.123";
    private String expression2 = "sin(2+35.6) + pi";
    private String expression3 = "e + pi";
    private String expression4 = "123+sin23.5^5";
    private String expression5 = "123!";

    private ArrayList<Token> tokenizedExpression1;
    private ArrayList<Token> tokenizedExpression2;
    private ArrayList<Token> tokenizedExpression3;


    void initializeExpressions() {
        tokenizedExpression1 = new ArrayList<>();
        tokenizedExpression2 = new ArrayList<>();
        tokenizedExpression3 = new ArrayList<>();

        //12.5+5*13.67-23\99.123
        tokenizedExpression1.add(new Token("12.5", Token.Priorities.NUMBER));
        tokenizedExpression1.add(new Token("+", Token.Priorities.SUM));
        tokenizedExpression1.add(new Token("5", Token.Priorities.NUMBER));
        tokenizedExpression1.add(new Token("*", Token.Priorities.PRODUCT));
        tokenizedExpression1.add(new Token("13.67", Token.Priorities.NUMBER));
        tokenizedExpression1.add(new Token("-", Token.Priorities.SUM));
        tokenizedExpression1.add(new Token("23", Token.Priorities.NUMBER));
        tokenizedExpression1.add(new Token("\\", Token.Priorities.PRODUCT));
        tokenizedExpression1.add(new Token("99.123", Token.Priorities.NUMBER));

        //sin(2+35.6) + pi
        tokenizedExpression2.add(new Token("sin", Token.Priorities.FUNCTION));
        tokenizedExpression2.add(new Token("(", Token.Priorities.PARENTHESES));
        tokenizedExpression2.add(new Token("2", Token.Priorities.NUMBER));
        tokenizedExpression2.add(new Token("+", Token.Priorities.SUM));
        tokenizedExpression2.add(new Token("35.6", Token.Priorities.NUMBER));
        tokenizedExpression2.add(new Token(")", Token.Priorities.PARENTHESES));
        tokenizedExpression2.add(new Token("+", Token.Priorities.SUM));
        tokenizedExpression2.add(new Token("pi", Token.Priorities.NUMBER));

        //e + pi
        tokenizedExpression3.add(new Token("e", Token.Priorities.NUMBER));
        tokenizedExpression3.add(new Token("+", Token.Priorities.SUM));
        tokenizedExpression3.add(new Token("pi", Token.Priorities.NUMBER));

    }

    @Test
    void isNumber() {
        assertTrue(Tokenizer.isNumber('0'));
        assertTrue(Tokenizer.isNumber('1'));
        assertTrue(Tokenizer.isNumber('2'));
        assertTrue(Tokenizer.isNumber('3'));
        assertTrue(Tokenizer.isNumber('4'));
        assertTrue(Tokenizer.isNumber('5'));
        assertTrue(Tokenizer.isNumber('6'));
        assertTrue(Tokenizer.isNumber('7'));
        assertTrue(Tokenizer.isNumber('8'));
        assertTrue(Tokenizer.isNumber('9'));
        assertTrue(Tokenizer.isNumber('.'));

        assertFalse(Tokenizer.isNumber('a'));
        assertFalse(Tokenizer.isNumber('!'));
        assertFalse(Tokenizer.isNumber('?'));
        assertFalse(Tokenizer.isNumber('('));
        assertFalse(Tokenizer.isNumber(')'));
        assertFalse(Tokenizer.isNumber('+'));
        assertFalse(Tokenizer.isNumber('-'));
        assertFalse(Tokenizer.isNumber('\\'));
        assertFalse(Tokenizer.isNumber('*'));
        assertFalse(Tokenizer.isNumber('^'));
    }

    @Test
    void isParenthesis() {
        assertTrue(Tokenizer.isParenthesis('('));
        assertTrue(Tokenizer.isParenthesis(')'));

        assertFalse(Tokenizer.isParenthesis('+'));
        assertFalse(Tokenizer.isParenthesis('-'));
        assertFalse(Tokenizer.isParenthesis('*'));
        assertFalse(Tokenizer.isParenthesis('\\'));
        assertFalse(Tokenizer.isParenthesis('^'));
        assertFalse(Tokenizer.isParenthesis('a'));
        assertFalse(Tokenizer.isParenthesis('b'));
    }

    @Test
    void isOperator() {
        assertTrue(Tokenizer.isOperator('+'));
        assertTrue(Tokenizer.isOperator('-'));
        assertTrue(Tokenizer.isOperator('*'));
        assertTrue(Tokenizer.isOperator('\\'));
        assertTrue(Tokenizer.isOperator('^'));

        assertFalse(Tokenizer.isOperator('('));
        assertFalse(Tokenizer.isOperator(')'));
    }

    @Test
    void isLetter() {
        assertTrue(Tokenizer.isLetter('a'));
        assertTrue(Tokenizer.isLetter('b'));
        assertTrue(Tokenizer.isLetter('y'));
        assertTrue(Tokenizer.isLetter('z'));
        assertTrue(Tokenizer.isLetter('A'));
        assertTrue(Tokenizer.isLetter('B'));
        assertTrue(Tokenizer.isLetter('Y'));
        assertTrue(Tokenizer.isLetter('Z'));

        assertFalse(Tokenizer.isLetter('+'));
        assertFalse(Tokenizer.isLetter('-'));
        assertFalse(Tokenizer.isLetter('*'));
        assertFalse(Tokenizer.isLetter('\\'));
        assertFalse(Tokenizer.isLetter('^'));
        assertFalse(Tokenizer.isLetter('('));
        assertFalse(Tokenizer.isLetter(')'));
        assertFalse(Tokenizer.isLetter('1'));
        assertFalse(Tokenizer.isLetter('2'));
        assertFalse(Tokenizer.isLetter('9'));
        assertFalse(Tokenizer.isLetter('.'));

    }

    boolean compareLists(ArrayList<Token> l1, ArrayList<Token> l2) {
        if (l1.size() != l2.size())
            return false;

        var iterator1 = l1.listIterator();
        var iterator2 = l2.listIterator();

        while (iterator1.hasNext()) {
            if (iterator1.next().compareTo(iterator2.next()) != 0)
                return false;
        }

        return true;
    }

    void printArrayList(ArrayList l1) {
        for (var v : l1) {
            System.out.println(v);
        }
    }

    @Test
    void tokenize() {
        initializeExpressions();
        ArrayList<Token> ans1 = Tokenizer.tokenize(expression1);
        assertTrue(compareLists(ans1, tokenizedExpression1));

        ArrayList<Token> ans2 = Tokenizer.tokenize(expression2);
        assertTrue(compareLists(ans2, tokenizedExpression2));

        ArrayList<Token> ans3 = Tokenizer.tokenize(expression3);
        assertTrue(compareLists(ans3, tokenizedExpression3));

        assertThrows(IllegalArgumentException.class, () -> Tokenizer.tokenize(expression4));
        assertThrows(IllegalArgumentException.class, () -> Tokenizer.tokenize(expression5));

    }
}