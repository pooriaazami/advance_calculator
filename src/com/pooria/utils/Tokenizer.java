package com.pooria.utils;

import java.util.ArrayList;

public class Tokenizer {

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
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
