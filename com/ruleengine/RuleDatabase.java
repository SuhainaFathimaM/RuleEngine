package com.ruleengine;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class RuleDatabase {
    private MongoCollection<Document> ruleCollection;

    public RuleDatabase() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("ruleEngine");
        ruleCollection = database.getCollection("rules");
    }

    public void saveRule(String ruleString) {
        Document ruleDocument = new Document("ruleString", ruleString)
                .append("createdAt", new java.util.Date())
                .append("updatedAt", new java.util.Date());
        ruleCollection.insertOne(ruleDocument);
    }

    public void retrieveRules() {
        for (Document doc : ruleCollection.find()) {
            System.out.println(doc.toJson());
        }
    }
}
