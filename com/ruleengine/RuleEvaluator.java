package com.ruleengine;

import java.util.Map;

public class RuleEvaluator {

    // Method to evaluate a single condition based on user data
    public boolean evaluateCondition(String condition, Map<String, Object> data) {
        String[] tokens = condition.split(" ");
        if (tokens.length != 3) {
            System.out.println("Error: Invalid condition format");
            return false;
        }

        String field = tokens[0];   // e.g., "age"
        String operator = tokens[1]; // e.g., ">"
        String value = tokens[2];   // e.g., "30"

        // Get the user's value for the field (e.g., age)
        Object userValue = data.get(field);

        // Handle null or missing field value
        if (userValue == null) {
            System.out.println("Error: Missing data for field: " + field);
            return false;
        }

        // Convert the user value and condition value to numbers for comparison
        if (userValue instanceof Number && isNumeric(value)) {
            double userNumericValue = ((Number) userValue).doubleValue();
            double conditionNumericValue = Double.parseDouble(value);

            // Evaluate based on the operator
            switch (operator) {
                case ">":
                    return userNumericValue > conditionNumericValue;
                case "<":
                    return userNumericValue < conditionNumericValue;
                case ">=":
                    return userNumericValue >= conditionNumericValue;
                case "<=":
                    return userNumericValue <= conditionNumericValue;
                case "==":
                    return userNumericValue == conditionNumericValue;
                case "!=":
                    return userNumericValue != conditionNumericValue;
                default:
                    System.out.println("Error: Unknown operator: " + operator);
                    return false;
            }
        } else if (userValue instanceof String) {
            // Handle string comparison for operators like == and !=
            switch (operator) {
                case "==":
                    return userValue.equals(value);
                case "!=":
                    return !userValue.equals(value);
                default:
                    System.out.println("Error: Unsupported operator for string comparison: " + operator);
                    return false;
            }
        } else {
            System.out.println("Error: Incompatible types or missing data for field: " + field);
            return false;
        }
    }

    // Utility method to check if a string is numeric
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Recursive rule evaluation (left and right nodes)
    public boolean evaluateRule(Node rule, Map<String, Object> data) {
        if (rule == null) {
            System.out.println("Error: Null rule node");
            return false;
        }

        if (rule.getType().equals("operand")) {
            return evaluateCondition(rule.getValue(), data);
        } else if (rule.getType().equals("operator")) {
            if (rule.getValue().equals("AND")) {
                return evaluateRule(rule.getLeft(), data) && evaluateRule(rule.getRight(), data);
            } else if (rule.getValue().equals("OR")) {
                return evaluateRule(rule.getLeft(), data) || evaluateRule(rule.getRight(), data);
            } else {
                System.out.println("Error: Unknown operator: " + rule.getValue());
                return false;
            }
        }
        return false;
    }
}
