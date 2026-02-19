public class RPNEvaluator {

    public static void main(String[] args) {

        String expr = "3 4 + 2 *";

        double result = evaluate(expr);

        System.out.println("Result: " + result);
    }

    public static double evaluate(String expression) {

        Stack<Double> stack = new LinkedStack<>();

        String[] tokens = expression.split(" ");

        for (String token : tokens) {

            if (token.equals("+") || token.equals("-") ||
                    token.equals("*") || token.equals("/")) {

                double b = stack.pop();
                double a = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                }

            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        return stack.pop();
    }
}