public class RPNEvaluator {

    public static void main(String[] args) {

        testExpression("3 4 + 2 *");
        testExpression("5 2 -");
        testExpression("3 +");
        testExpression("4 5");
        testExpression("2 a +");
        testExpression("2 3 ^");
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