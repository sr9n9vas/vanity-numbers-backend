package com.vanity.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class VanityDatabaseService {


    public void save(List<String> vanityNumbers, String phoneNumber) {
        final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
        String vanityTable = "VanityNumbers";
        saveVanityNumbers(ddb, vanityTable, phoneNumber, vanityNumbers);

    }

    private void saveVanityNumbers(AmazonDynamoDB ddb, String table, String phoneNumber, List<String> vanityNumbers) {
        try {
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("VanityId", new AttributeValue(UUID.randomUUID().toString()));
            item.put("PhoneNumber", new AttributeValue(phoneNumber));
            item.put("VanityNumbers", new AttributeValue(vanityNumbers));

            PutItemRequest request = new PutItemRequest();
            request.setTableName(table);
            request.setItem(item);
            ddb.putItem(request);

        } catch (AmazonDynamoDBException e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting item into table: " + e.getMessage());
        }
    }

}
