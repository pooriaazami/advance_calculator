package com.pooria.calculator;

import com.pooria.utils.Token;
import com.pooria.utils.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    String expression1 = "12+13.5*345\\123.7";

    ArrayList<Token> postOrderExpression1;

    void initialize() {
        postOrderExpression1 = new ArrayList<>();

        postOrderExpression1.add(new Token("12", Token.Priorities.NUMBER));
        postOrderExpression1.add(new Token("13.5", Token.Priorities.NUMBER));
        postOrderExpression1.add(new Token("+", Token.Priorities.SUM));
        postOrderExpression1.add(new Token("345", Token.Priorities.NUMBER));
        postOrderExpression1.add(new Token("123.7", Token.Priorities.NUMBER));
        postOrderExpression1.add(new Token("\\", Token.Priorities.PRODUCT));
        postOrderExpression1.add(new Token("*", Token.Priorities.PRODUCT));
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

    @Test
    void translateToPostOrder() {
        initialize();

        ArrayList<Token> inOrderExpression1 = Tokenizer.tokenize(expression1);
        ArrayList<Token> ans1 = Calculator.translateToPostOrder(inOrderExpression1);
        assertTrue(compareLists(ans1, postOrderExpression1));
    }
}