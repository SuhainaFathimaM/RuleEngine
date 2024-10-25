package com.ruleengine;

public class RuleParser {

    // Method to create an AST from a rule string
    public Node createRule(String ruleString) {
        String[] tokens = ruleString.split(" ");
        if (tokens.length < 3) {
            System.out.println("Error: Rule string is too short or invalid.");
            return null;
        }

        // Left operand (e.g., "age > 30")
        Node leftOperand = new Node("operand", null, null, tokens[0] + " " + tokens[1] + " " + tokens[2]);

        // Check if there's an operator (AND, OR) and potentially a right operand
        Node operatorNode = null;
        if (tokens.length > 3) {
            String operator = tokens[3];  // AND, OR, etc.
            operatorNode = new Node("operator", leftOperand, null, operator);

            // Right operand (e.g., "department = 'Sales'")
            if (tokens.length > 4) {
                if (tokens.length >= 7) {
                    Node rightOperand = new Node("operand", null, null, tokens[4] + " " + tokens[5] + " " + tokens[6]);
                    operatorNode.setRight(rightOperand);
                } else {
                    System.out.println("Error: Rule string is missing parts for the right operand.");
                    return null;
                }
            }
        }

        return operatorNode != null ? operatorNode : leftOperand;  // Return the full rule or the operand node
    }

    // Method to modify existing rules in the AST
    public void modifyRule(Node node, String newValue) {
        if (node.getType().equals("operand")) {
            node.setValue(newValue);
        } else {
            // Recursive traversal for complex rules
            if (node.getLeft() != null) {
                modifyRule(node.getLeft(), newValue);
            }
            if (node.getRight() != null) {
                modifyRule(node.getRight(), newValue);
            }
        }
    }

    // Method to serialize a rule into a string (convert AST to string)
    public String serializeRule(Node rule) {
        if (rule == null) return "";
        String left = rule.getLeft() != null ? serializeRule(rule.getLeft()) : "";
        String right = rule.getRight() != null ? serializeRule(rule.getRight()) : "";
        return rule.getValue() + " " + left + " " + right;
    }

    // Method to deserialize a rule string into an AST (convert string to AST)
    public Node deserializeRule(String ruleString) {
        return createRule(ruleString);
    }
}
