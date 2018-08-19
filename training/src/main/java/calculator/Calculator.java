package calculator;

import java.math.*;
import java.util.*;

class Calculator {

    BigDecimal calculate(String expression){
        if (expression == null || expression.length() == 0){
            return null;
        }

        try{
            return round(evaluate(expression));
        }
        catch(RuntimeException e){
            return null;
        }
    }

    private double evaluate(String expression){
        double result = 0.0;
        StringBuilder currentTerm = new StringBuilder();
        List<Character> signs = Arrays.asList('-', '+');
        Character currentSign = ' ';
        StringBuilder subExpression = new StringBuilder();
        int subExpressionIndex = -1;

        for (int i = 0; i < expression.length(); i++) {/*replacing expressions inside parentheses with its values*/
            if (expression.charAt(i) == '('){
                int parenthesesCounter = 1;/*counting parentheses in case of embedded parentheses*/
                subExpressionIndex = i;
                while(++i < expression.length()){
                    char current = expression.charAt(i);

                    if(current == '('){
                        parenthesesCounter++;
                    }
                    if(current == ')'){
                        parenthesesCounter--;
                    }
                    if(parenthesesCounter == 0){
                        break;
                    }
                    subExpression.append(expression.charAt(i));
                }

                if(i < expression.length() - 1){
                    i++;
                }
            }
            if(subExpressionIndex != -1){/*replacing expression with its value*/
                expression = expression.replace(expression.substring(subExpressionIndex, subExpressionIndex +
                subExpression.length() + 2), String.valueOf(evaluate(subExpression.toString())));
                subExpression = new StringBuilder();
                subExpressionIndex = -1;
            }
        }

        for (int i = 0; i < expression.length(); i++) {
        /*dividing expression on terms and signs*/
            char current = expression.charAt(i);

            if(signs.contains(current) || i == expression.length() - 1){
                if(i == expression.length() - 1){
                    currentTerm.append(current);
                }
                boolean isEndOfTerm = true;

                if(current == '-'){
                    isEndOfTerm = false;
                    for (int j = i - 1; j >= 0; j--) {
                        char checkedChar = expression.charAt(j);
                        if(signs.contains(checkedChar) || checkedChar == '*' || checkedChar == '/' || checkedChar == '('){
                            break;
                        }
                        else{
                            if(expression.charAt(j) != ' '){
                                isEndOfTerm = true;
                                break;
                            }
                        }
                    }
                }

                if(isEndOfTerm){
                    double termResult = calculateTerm(currentTerm.toString());
                    result = makingOperation(result, termResult, currentSign);

                    currentTerm = new StringBuilder();
                    currentSign = current;
                }
                else {
                    currentTerm.append(current);
                }
            }
            else{
                currentTerm.append(current);
            }
        }
        return result;
    }

    private double calculateTerm(String term){
        double result = 0.0;
        List<Character> signs = Arrays.asList('*', '/');
        StringBuilder number = new StringBuilder();
        char currentSign = ' ';

        for (int i = 0; i < term.length(); i++) {
            char current = term.charAt(i);
            if(!signs.contains(current) && current != ' '){
                number.append(current);
            }

            if((signs.contains(current) || i == term.length() - 1)){
                double num = parseNumber(number.toString());
                result = makingOperation(result, num, currentSign);

                number = new StringBuilder();
                currentSign = term.charAt(i);
            }
        }
        return result;
    }

    private double parseNumber(String number){
        if (number.contains(".")){
            return Double.parseDouble(number);
        }
        else{
            return (double) Integer.parseInt(number);
        }
    }

    private double makingOperation(double num1, double num2, char sign){
        switch (sign){
            case '*':
                num1 *= num2;
                break;
            case '/':
                num1 /= num2;
                break;
            case '+':
                num1 += num2;
                break;
            case '-':
                num1 -= num2;
                break;
            case ' ':/*default operation, means there's the first number in expression*/
                num1 = num2;
                break;
            default:
                break;
        }
        return num1;
    }

    private BigDecimal round(double number){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(number));
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        String decimal = bigDecimal.abs().toString();

        if (decimal.length() > 6 && (int)(decimal.charAt(6)) < 5){
            roundingMode = RoundingMode.HALF_DOWN;
        }

        bigDecimal = bigDecimal.setScale(4, roundingMode);
        return bigDecimal;
    }
}
