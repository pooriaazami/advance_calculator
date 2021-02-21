package com.pooria.calculator;

import com.pooria.utils.Token;
import com.pooria.utils.Tokenizer;

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
                if (current.isLeftParenthesis())
                    stack.add(current);
                else if (current.isRightParenthesis()) {
                    while (true) {
                        if (stack.peek().isLeftParenthesis())
                            break;

                        ans.add(stack.pop());
                    }
                    stack.pop();
                } else {
                    while (stack.size() > 0 && current.compareTo(stack.peek()) < 1) {
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

    private static void calculateTokens(Token operator, Stack<Double> numbers) {
        double operand1, operand2;
        switch (operator.getValue()) {
            case "+":
                operand1 = numbers.pop();
                operand2 = numbers.pop();
                numbers.push(operand1 + operand2);
                return;
            case "-":
                operand1 = numbers.pop();
                operand2 = numbers.pop();
                numbers.push(operand2 - operand1);
                return;
            case "*":
                operand1 = numbers.pop();
                operand2 = numbers.pop();
                numbers.push(operand1 * operand2);
                return;
            case "/":
            case "\\":
                operand1 = numbers.pop();
                operand2 = numbers.pop();
                numbers.push(operand2 / operand1);
                return;
            case "^":
            case "power":
                operand1 = numbers.pop();
                operand2 = numbers.pop();
                numbers.push(Math.pow(operand2, operand1));
                return;
            case "sqrt":
                operand1 = numbers.pop();
                numbers.push(Math.sqrt(operand1));
                return;
            case "sin":
                operand1 = numbers.pop();
                numbers.push(Math.sin(operand1));
                return;
            case "cos":
                operand1 = numbers.pop();
                numbers.push(Math.cos(operand1));
                return;
            case "tan":
            case "tg":
                operand1 = numbers.pop();
                numbers.push(Math.tan(operand1));
                return;
            case "cot":
                operand1 = numbers.pop();
                numbers.push(1 / Math.tan(operand1));
                return;
            case "sinh":
                operand1 = numbers.pop();
                numbers.push(Math.sinh(operand1));
                return;
            case "cosh":
                operand1 = numbers.pop();
                numbers.push(Math.cosh(operand1));
                return;
            case "tanh":
                operand1 = numbers.pop();
                numbers.push(Math.tanh(operand1));
                return;
            case "log":
                operand1 = numbers.pop();
                numbers.push(Math.log10(operand1));
                return;
            case "ln":
                operand1 = numbers.pop();
                numbers.push(Math.log(operand1));
                return;
            case "exp":
                operand1 = numbers.pop();
                numbers.push(Math.exp(operand1));
                return;
            case "abs":
                operand1 = numbers.pop();
                numbers.push(Math.abs(operand1));
        }
    }

    public static double calculatePostOrder(ArrayList<Token> postOrder) {
        Stack<Double> stack = new Stack<>();

        int len = postOrder.size();
        for (int i = 0; i < len; i++) {
            Token current = postOrder.get(i);

            if (current.isNumber()) {
                stack.push(current.parseToDouble());
            } else {
                calculateTokens(current, stack);
            }
        }

        if (stack.size() != 1)
            throw new RuntimeException("Invalid expression");

        return stack.peek();
    }

    public static double calculate(String expression) {
        ArrayList<Token> tokenizedExpression = Tokenizer.tokenize(expression);
        ArrayList<Token> postOrderExpression = Calculator.translateToPostOrder(tokenizedExpression);

        return calculatePostOrder(postOrderExpression);
    }
}
