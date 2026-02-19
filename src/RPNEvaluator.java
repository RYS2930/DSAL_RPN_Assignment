import java.util.Scanner;

public class RPNEvaluator {

    public static void main(String[] args) {

//        ===== Test Cases (Evidence of Testing) =====
//        testExpression("3 4 + 2 *");
//        testExpression("5 2 -");
//        testExpression("3 +");
//        testExpression("4 5");
//        testExpression("2 a +");
//        testExpression("2 3 ^");

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter an RPN expression (or type 'exit' to quit):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Program terminated.");
                break;
            }

            try {
                double result = evaluate(input);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("ERROR: Invalid expression.");
            }
        }

        scanner.close();
    }

    public static double evaluate(String expression) {

        Stack<Double> stack = new LinkedStack<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {

            if (token.equals("+") || token.equals("-") ||
                    token.equals("*") || token.equals("/") ||
                    token.equals("^")) {

                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid expression");
                }

                double b = stack.pop();

                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid expression");
                }

                double a = stack.pop();

                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                    case "^": stack.push(Math.pow(a, b)); break;
                }

            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        double result = stack.pop();

        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return result;
    }

    private static void testExpression(String expr) {
        try {
            double result = evaluate(expr);
            System.out.println(expr + " = " + result);
        } catch (Exception e) {
            System.out.println(expr + " → ERROR: " + e.getMessage());
        }
    }
}