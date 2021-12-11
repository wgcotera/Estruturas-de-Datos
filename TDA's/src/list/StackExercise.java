package list;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author wgcotera
 */
public class StackExercise {
    public static void main(String[] args) {
        
        System.out.println("CONVERT TO POST-FIX");
        String c = "5 + 12 / 3 * 8 + 2";
        System.out.println("Original input: \t" + c);
        System.out.println("Post-fix output:  \t"+ convertToPostFix(c));     
        
        System.out.println("\n");
        
        String d = "A * B / A + C";
        System.out.println("Original input: \t" + d);
        System.out.println("Post-fix output:  \t"+ convertToPostFix(d));      
        
        System.out.println("\n");
        
        System.out.println("EVALUATE POST-FIX");
        String e = "6 12 3 + - 31 18 27 / + * 2 ^ 3 +";
        System.out.println("Original input: \t" + e );
        System.out.println("Post-fix Evaluation: \t" + evaluatePostFix(e));
    }
    
    public static String convertToPostFix(String operation) {
        
        // create a stack
        Stack<String> optr = new Stack<>();
        
        String[] data = operation.split(" ");
        String postFix = "";

        for (String o : data) {
            if(o.equals(""));
            
            else if (isOperand(o)) {
                postFix += o + " ";
            } 
            
            else {
                if (optr.isEmpty()) {
                    optr.push(o);
                } else {
                    if (getPriority(optr.peek()) < getPriority(o)) {
                        optr.push(o);                        
                    } else{
                        while (!optr.isEmpty() && !(getPriority(optr.peek()) < getPriority(o))) {
                            postFix += optr.pop() + " ";                            
                        }
                        optr.push(o);
                    }
                }                
            }
        }
        while (!optr.isEmpty()) {
            postFix += optr.pop() + " "; 
        }
        return postFix;        
    }
    
        public static Double evaluatePostFix(String expresion) {
        
        //create a stack
        Stack<Double> nums = new Stack<>();
        
        String[] parts = expresion.split(" ");
        
        for (String s : parts) {
            if(s.equals(""));
            
            else if (isOperand(s)) {
                nums.push(Double.parseDouble(s));
            }
            
            else {
                double operand2 = nums.pop();
                double operand1 = nums.pop();
                double result = operate (operand1, operand2,  s);
                nums.push(result);
            }
        }
        return nums.pop();        
    }
    
        // static method that returns true the string type parameter that it receives is an operator.
    public static boolean isOperator(String operator) {
        if ("/".equals(operator) || "*".equals(operator) || "+".equals(operator) || "-".equals(operator) || "^".equals(operator)) {
            return true;
        } 
        return false;
    }
    
        // static method that returns true the string type parameter it receives is a number.
    public static boolean isOperand(String operand) {
        return !(isOperator(operand));
    }
    
    public static int getPriority(String operator) {
        switch (operator) {
            case "^":
                return 5;
            case "/":
                return 4;
            case "*":
                return 4;
            case "+":
                return 3;
            case "-" :
                return 3;
            default:
                return 0;
        }
    }

    private static double operate(double operand1, double operand2, String operator) {
        switch (operator) {
            case "/":
                return operand1/operand2;
            case "*":
                return operand1*operand2;
            case "+":
                return operand1+operand2;
            case "-" :
                return operand1-operand2;
            default:
                return Math.pow(operand1, operand2);
        }
    }
    

}
