# Rule Engine with Abstract Syntax Tree (AST)

A dynamic rule engine application that evaluates user eligibility based on a customizable set of conditions, such as age, department, income, and experience. This system uses an Abstract Syntax Tree (AST) to represent conditional rules, enabling the creation, combination, modification, and evaluation of rules with ease. MongoDB is used for data storage, allowing persistent rule storage and efficient retrieval.

## Table of Contents

1. [Features](#features)
2. [Data Structure](#data-structure)
3. [Setup and Requirements](#setup-and-requirements)
4. [Project Structure](#project-structure)
5. [Usage](#usage)
6. [API Design](#api-design)
7. [Future Enhancements](#future-enhancements)

## Features

- **Dynamic Rule Creation**: Build rules dynamically using conditional expressions.
- **AST-based Rule Representation**: Efficiently stores and processes complex rules.
- **Rule Modification**: Modify conditions or operators in existing rules.
- **MongoDB Integration**: Store and retrieve rules from MongoDB for persistent storage.
- **User Evaluation**: Evaluate if a user meets specific conditions based on their profile data.
- **Error Handling**: Handles invalid rules and unsupported data types gracefully.

## Data Structure

The system represents rules as an Abstract Syntax Tree (AST) composed of `Node` objects. Each `Node` has the following fields:
- **type**: Specifies whether the node is an `operator` (AND/OR) or `operand` (condition).
- **left** and **right**: References to child nodes for operators.
- **value**: Holds the operand's condition (e.g., `age > 30`) for leaf nodes.

Example rule represented in AST:
- Rule: `((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)`

## Setup and Requirements

### Prerequisites
- Java JDK 8 or higher
- MongoDB server running locally on `localhost:27017`
- MongoDB Java Driver JAR file in the `lib` folder (download from [MongoDB Java Driver](https://repo1.maven.org/maven2/org/mongodb/mongo-java-driver/3.12.10/mongo-java-driver-3.12.10.jar))

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/RuleEngine.git
   cd RuleEngine
   ```
   
2. **Compile the Project**:
   ```bash
   javac -cp ".;lib/mongo-java-driver-3.12.10.jar" com/ruleengine/*.java
   ```
   
3. **Run the Application**:
   ```bash
   java -cp ".;lib/mongo-java-driver-3.12.10.jar" com.ruleengine.RuleEngineMain
   ```

## Project Structure
```bash
RuleEngine/ 
│
├── com/ruleengine/
│   ├── Node.java            # Defines AST node structure
│   ├── RuleEvaluator.java   # Evaluates AST against user data
│   ├── RuleParser.java      # Parses rule strings into AST
│   ├── RuleCombiner.java    # Combines multiple ASTs
│   ├── RuleDatabase.java    # Handles MongoDB operations
│   └── RuleEngineMain.java  # Main entry point of the application
│
├── lib/
│   └── mongo-java-driver-3.12.10.jar
│
├── .gitignore
└── README.md

```

## Usage
### Running the Rule Engine
1. **Create Rules** : Define rules using the create_rule function.
2. **Combine Rules** : Use combine_rules to merge rules with logical operators.
3. **Evaluate Rules** : Pass user data into evaluate_rule to check if conditions are met.

## API Design
1. create_rule(rule_string): Parses a rule string to create an AST.
2. combine_rules(rules): Combines multiple rules using logical operators.
3. evaluate_rule(json_data): Evaluates the AST against user-provided data.
4. **Database Functions**:
   - saveRule(name, rootNode): Saves an AST to MongoDB.
   - getRule(name): Retrieves an AST from MongoDB by name.

## Future Enhancements
1. **User-defined Functions**: Add support for custom functions within rule logic.
2. **Validation**: Implement validation for data types and missing fields.
3. **Advanced Modifications**: Allow adding/removing sub-expressions within the AST.
4. **UI Component**: Develop a simple web UI to define and test rules visually.

   
