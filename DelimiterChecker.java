package com.mycompany.delimiterchecker;

/* Description: A stack-based delimiter checker that traces stack operations
   and determines if parentheses, brackets, and braces are correctly matched and nested.
Programmed by: Khyle Barnes BSIT
Last Modified: October 30, 2026
Version: 1.0
Acknowledgements: Java Documentation (java.util.Stack)
*/

import java.util.Scanner;
import java.util.Stack;

public class DelimiterChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter sequence: ");
        String input = scanner.nextLine();

        System.out.println("\n--- Stack Execution Trace ---");
        boolean isValid = checkDelimiters(input);

        System.out.println("\n--- Result ---");
        if (isValid) {
            System.out.println("Result: Valid");
        } else {
            System.out.println("Result: Invalid");
        }
        
        scanner.close();
    }

    public static boolean checkDelimiters(String sequence) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < sequence.length(); i++) {
            char ch = sequence.charAt(i);

            // Push opening delimiters
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
                System.out.println("Read: " + ch + " | Operation: PUSH | Stack: " + stack);
            } 
            // Process closing delimiters
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    System.out.println("Read: " + ch + " | Error: Closing delimiter with no matching opening delimiter.");
                    return false;
                }

                char top = stack.peek();
                System.out.println("Read: " + ch + " | Operation: PEEK -> " + top);

                if (isMatchingPair(top, ch)) {
                    stack.pop();
                    System.out.println("Operation: POP | Stack: " + stack);
                } else {
                    System.out.println("Error: Mismatched delimiter. Top was '" + top + "' but encountered '" + ch + "'.");
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("Error: Unmatched opening delimiter(s) remaining on stack: " + stack);
            return false;
        }

        return true;
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }
}