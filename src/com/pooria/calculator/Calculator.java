package com.pooria.calculator;

import com.pooria.utils.Token;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator {

    public static ArrayList<Token> translateToPostOrder(ArrayList<Token> inOrder) {
        ArrayList<Token> ans = new ArrayList<>();
        Stack<Token> stack = new Stack<>();

        int len = inOrder.size();
        for (int i = 0; i < len; i++) {
            Token current = inOrder.get(i);

            if (current.isNumber()) {
                ans.add(current);
            } else {
                if (current.isRightParenthesis()) {
                    while (!stack.peek().isLeftParenthesis() && stack.size() > 0) {
                        ans.add(stack.pop());
                    }
                } else {
                    while (stack.size() > 0 && current.compareTo(stack.peek()) == 1) {
                        ans.add(stack.pop());
                    }

                    stack.push(current);
                }
            }
        }

        while (stack.size() > 0) {
            ans.add(stack.pop());
        }

        return ans;
    }
}
