package com.aws.demo.dynamodb.controller;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DynamoDbController {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @PostMapping("/createTable")
    public TableDescription createTable() throws InterruptedException {
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

        CreateTableRequest tableRequest = new CreateTableRequest()
                .withTableName("Movies")
                .withKeySchema(new KeySchemaElement("year", KeyType.HASH), new KeySchemaElement("title", KeyType.RANGE))
                .withAttributeDefinitions(new AttributeDefinition("year", ScalarAttributeType.N), new AttributeDefinition("title", ScalarAttributeType.S))
                .withBillingMode(BillingMode.PAY_PER_REQUEST);

        Table table = dynamoDB.createTable(tableRequest);
        TableDescription tableDescription = table.waitForActive();
        return tableDescription;
    }
}
