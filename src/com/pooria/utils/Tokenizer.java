package com.pooria.utils;

import java.util.ArrayList;

public class Tokenizer {

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isLeftParenthesis(char c) {
        return c == '(';
    }

    public static boolean isRightParenthesis(char c) {
        return c == ')';
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '\\' || c == '^';
    }

    public static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static ArrayList<Token> tokenize(String expression) {
        ArrayList<Token> ans = new ArrayList<>();

        int start = 0;
        int len = expression.length();
        for (int i = 0; i < len; i++) {
            if (isNumber(expression.charAt(i)) || expression.charAt(i) == '.')
                continue;
            else {
                ans.add(new Token(expression.substring(start, i), Token.Priorities.NUMBER));
                start = i;
            }
        }

        return ans;
    }
}
