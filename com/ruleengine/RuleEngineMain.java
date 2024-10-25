package com.ruleengine;

import java.util.*;

public class RuleEngineMain {
    public static void main(String[] args) {
        RuleParser parser = new RuleParser();
        RuleEvaluator evaluator = new RuleEvaluator();
        RuleCombiner combiner = new RuleCombiner();
    
        // Create rules
        Node rule1 = parser.createRule("age > 30");
        Node rule2 = parser.createRule("salary > 50000");
    
        // Check if rules were created successfully
        if (rule1 == null || rule2 == null) {
            System.out.println("Error: One or more rules are invalid.");
            return;
        }

        // Modify the rule dynamically (e.g., change age condition)
        parser.modifyRule(rule1, "age > 25");
    
        // Combine rules
        Node combinedRule = combiner.combineRules(rule1, rule2, "AND");
    
        // User data
        Map<String, Object> userData = new HashMap<>();
        userData.put("age", 35);
        userData.put("salary", 60000);
    
        // Evaluate rule
        boolean isEligible = evaluator.evaluateRule(combinedRule, userData);
        System.out.println("User eligible: " + isEligible);
    }
}
