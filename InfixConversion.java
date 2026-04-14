import java.util.Stack;
import java.util.Scanner;

public class InfixConversion {

    // Function to check precedence
    static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Function to convert Infix to Postfix
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If operand, add to result
            if (Character.isLetterOrDigit(c)) {
                result += c;
            }

            // If '(', push to stack
            else if (c == '(') {
                stack.push(c);
            }

            // If ')', pop until '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            }

            // Operator
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Function to convert Infix to Prefix
    static String infixToPrefix(String exp) {
        StringBuilder input = new StringBuilder(exp);
        input.reverse();

        // Swap brackets
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(')
                input.setCharAt(i, ')');
            else if (input.charAt(i) == ')')
                input.setCharAt(i, '(');
        }

        String postfix = infixToPostfix(input.toString());

        StringBuilder prefix = new StringBuilder(postfix);
        return prefix.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an infix expression: ");
        String infix = scanner.nextLine();

        System.out.println("Infix   : " + infix);
        System.out.println("Postfix : " + infixToPostfix(infix));
        System.out.println("Prefix  : " + infixToPrefix(infix));

        scanner.close();
    }
}