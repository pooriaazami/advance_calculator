package com.pooria.utils;

import java.util.ArrayList;

public class Tokenizer {

    public static boolean isNumber(char c) {
        return (c >= '0' && c <= '9') || c == '.';
    }

    public static boolean isParenthesis(char c) {
        return c == '(' || c == ')';
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '\\' || c == '^';
    }

    public static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private static void processBuffer(String buffer, boolean isBufferNumeric, ArrayList<Token> tokens) {
        if (buffer.length() > 0) {
            if (isBufferNumeric) {
                tokens.add(new Token(buffer, Token.Priorities.NUMBER));
            } else {
                tokens.add(new Token(buffer, Token.Priorities.FUNCTION));
            }
        }
    }

    public static ArrayList<Token> tokenize(String expression) {
        ArrayList<Token> ans = new ArrayList<>();

        String buffer = "";
        boolean isBufferNumeric = true;
        int len = expression.length();
        for (int i = 0; i < len; i++) {
            if (isParenthesis(expression.charAt(i))) {
                processBuffer(buffer, isBufferNumeric, ans);
                buffer = "";
                ans.add(new Token(String.valueOf(expression.charAt(i)), Token.Priorities.PARENTHESES));
            } else if (isOperator(expression.charAt(i))) {
                processBuffer(buffer, isBufferNumeric, ans);
                switch (expression.charAt(i)) {
                    case '+' -> ans.add(new Token("+", Token.Priorities.SUM));
                    case '-' -> ans.add(new Token("-", Token.Priorities.SUM));
                    case '*' -> ans.add(new Token("*", Token.Priorities.PRODUCT));
                    case '\\' -> ans.add(new Token("\\", Token.Priorities.PRODUCT));
                    case '^' -> ans.add(new Token("^", Token.Priorities.FUNCTION));
                }
            } else if (isLetter(expression.charAt(i))) {
                buffer += expression.charAt(i);
                isBufferNumeric = false;
            } else if (isNumber(expression.charAt(i))) {
                buffer += expression.charAt(i);
                isBufferNumeric = true;
            } else {
                throw new IllegalArgumentException("Invalid expression");
            }

        }

        processBuffer(buffer, isBufferNumeric, ans);

        return ans;
    }
}
