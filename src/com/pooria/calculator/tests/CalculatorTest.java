package com.pooria.calculator.tests;

import com.pooria.calculator.Calculator;
import com.pooria.utils.Token;
import com.pooria.utils.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    String expression1 = "12+13.5*345\\123.7";
    String expression2 = "1+0.5*(1298/(27.63-123))*25.67";
    String expression3 = "sin(23.57+(123.67*33.9+4)/(12 + 1))";
    String expression4 = "sqrt(12.5^3.75+sin(22.5+2))+cos(123/tan(12))";

    ArrayList<Token> postOrderExpression1;
    ArrayList<Token> postOrderExpression2;
    ArrayList<Token> postOrderExpression3;
    ArrayList<Token> postOrderExpression4;

    void initialize() {
        postOrderExpression1 = new ArrayList<>();
        postOrderExpression2 = new ArrayList<>();
        postOrderExpression3 = new ArrayList<>();
        postOrderExpression4 = new ArrayList<>();

        //12+13.5*345\123.7
        postOrderExpression1.add(new Token("12", Token.Priorities.NUMBER));
        postOrderExpression1.add(new Token("13.5", Token.Priorities.NUMBER));
        postOrderExpression1.add(new Token("345", Token.Priorities.NUMBER));
        postOrderExpression1.add(new Token("*", Token.Priorities.PRODUCT));
        postOrderExpression1.add(new Token("123.7", Token.Priorities.NUMBER));
        postOrderExpression1.add(new Token("\\", Token.Priorities.PRODUCT));
        postOrderExpression1.add(new Token("+", Token.Priorities.SUM));

        //1+0.5*(1298/(27.63-123))*25.67
        postOrderExpression2.add(new Token("1", Token.Priorities.NUMBER));
        postOrderExpression2.add(new Token("0.5", Token.Priorities.NUMBER));
        postOrderExpression2.add(new Token("1298", Token.Priorities.NUMBER));
        postOrderExpression2.add(new Token("27.63", Token.Priorities.NUMBER));
        postOrderExpression2.add(new Token("123", Token.Priorities.NUMBER));
        postOrderExpression2.add(new Token("-", Token.Priorities.SUM));
        postOrderExpression2.add(new Token("\\", Token.Priorities.PRODUCT));
        postOrderExpression2.add(new Token("*", Token.Priorities.PRODUCT));
        postOrderExpression2.add(new Token("25.67", Token.Priorities.NUMBER));
        postOrderExpression2.add(new Token("*", Token.Priorities.PRODUCT));
        postOrderExpression2.add(new Token("+", Token.Priorities.SUM));

        //sin(23.57+(123.67*33.9+4)/(12 + 1))
        postOrderExpression3.add(new Token("23.57", Token.Priorities.NUMBER));
        postOrderExpression3.add(new Token("123.67", Token.Priorities.NUMBER));
        postOrderExpression3.add(new Token("33.9", Token.Priorities.NUMBER));
        postOrderExpression3.add(new Token("*", Token.Priorities.PRODUCT));
        postOrderExpression3.add(new Token("4", Token.Priorities.NUMBER));
        postOrderExpression3.add(new Token("+", Token.Priorities.SUM));
        postOrderExpression3.add(new Token("12", Token.Priorities.NUMBER));
        postOrderExpression3.add(new Token("1", Token.Priorities.NUMBER));
        postOrderExpression3.add(new Token("+", Token.Priorities.SUM));
        postOrderExpression3.add(new Token("\\", Token.Priorities.PRODUCT));
        postOrderExpression3.add(new Token("+", Token.Priorities.SUM));
        postOrderExpression3.add(new Token("sin", Token.Priorities.FUNCTION));

        //sqrt(12.5^3.75+sin(22.5+2))+cos(123/tan(12))
        postOrderExpression4.add(new Token("12.5", Token.Priorities.NUMBER));
        postOrderExpression4.add(new Token("3.75", Token.Priorities.NUMBER));
        postOrderExpression4.add(new Token("^", Token.Priorities.FUNCTION));
        postOrderExpression4.add(new Token("22.5", Token.Priorities.NUMBER));
        postOrderExpression4.add(new Token("2", Token.Priorities.NUMBER));
        postOrderExpression4.add(new Token("+", Token.Priorities.SUM));
        postOrderExpression4.add(new Token("sin", Token.Priorities.FUNCTION));
        postOrderExpression4.add(new Token("+", Token.Priorities.SUM));
        postOrderExpression4.add(new Token("sqrt", Token.Priorities.FUNCTION));
        postOrderExpression4.add(new Token("123", Token.Priorities.NUMBER));
        postOrderExpression4.add(new Token("12", Token.Priorities.NUMBER));
        postOrderExpression4.add(new Token("tan", Token.Priorities.FUNCTION));
        postOrderExpression4.add(new Token("\\", Token.Priorities.PRODUCT));
        postOrderExpression4.add(new Token("cos", Token.Priorities.FUNCTION));
        postOrderExpression4.add(new Token("+", Token.Priorities.SUM));
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
    void translateToPostOrder() {
        initialize();

        ArrayList<Token> inOrderExpression1 = Tokenizer.tokenize(expression1);
        ArrayList<Token> ans1 = Calculator.translateToPostOrder(inOrderExpression1);
        assertTrue(compareLists(ans1, postOrderExpression1));

        ArrayList<Token> inOrderExpression2 = Tokenizer.tokenize(expression2);
        ArrayList<Token> ans2 = Calculator.translateToPostOrder(inOrderExpression2);
        assertTrue(compareLists(ans2, postOrderExpression2));

        ArrayList<Token> inOrderExpression3 = Tokenizer.tokenize(expression3);
        ArrayList<Token> ans3 = Calculator.translateToPostOrder(inOrderExpression3);
        assertTrue(compareLists(ans3, postOrderExpression3));

        ArrayList<Token> inOrderExpression4 = Tokenizer.tokenize(expression4);
        ArrayList<Token> ans4 = Calculator.translateToPostOrder(inOrderExpression4);
        assertTrue(compareLists(ans4, postOrderExpression4));
    }
}