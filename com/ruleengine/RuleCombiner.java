package com.ruleengine;

public class RuleCombiner {

    // Method to combine two rules with a logical operator (AND/OR)
    public Node combineRules(Node rule1, Node rule2, String operator) {
        // Null check for input rules
        if (rule1 == null || rule2 == null) {
            System.out.println("Error: One or both rules are null.");
            return null;
        }

        // Validate the operator
        if (!operator.equals("AND") && !operator.equals("OR")) {
            System.out.println("Error: Invalid operator. Only 'AND' and 'OR' are supported.");
            return null;
        }

        // Combine rules with the given logical operator
        Node root = new Node("operator", rule1, rule2, operator);
        return root;
    }
}
