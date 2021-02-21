package com.pooria.calculator;

import com.pooria.utils.Token;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator {

    private static boolean isLeftParenthesis(Token t) {
        return t.compareTo(new Token("(", Token.Priorities.PARENTHESES)) == 0;
    }

    private static boolean isRightParenthesis(Token t) {
        return t.compareTo(new Token(")", Token.Priorities.PARENTHESES)) == 0;
    }

    public static ArrayList<Token> translateToPostOrder(ArrayList<Token> inOrder) {
        ArrayList<Token> ans = new ArrayList<>();
        Stack<Token> stack = new Stack<>();

        int len = inOrder.size();
        for (int i = 0; i < len; i++) {
            Token current = inOrder.get(i);

            if (current.isNumber()) {
                ans.add(current);
            } else {

            }
        }

        return ans;
    }
}
