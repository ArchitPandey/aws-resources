package com.aws.demo.dynamodb.controller;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.aws.demo.dynamodb.util.JsonUtils;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DynamoDbController {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private JsonFactory jsonFactory;

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

    @PostMapping("/loadTable")
    public List<PutItemOutcome> loadTable() throws IOException {

        ClassPathResource resource = new ClassPathResource("static/moviedata.json");
        JsonParser parser = jsonFactory.createParser(resource.getInputStream());
        JsonNode root = new ObjectMapper().readTree(parser);

        List<Item> items = JsonUtils.dynamoDBItems(root);

        DynamoDB dynamoDb = new DynamoDB(amazonDynamoDB);
        Table table = dynamoDb.getTable("Movies");

        List<PutItemOutcome> result = new ArrayList<>();
        items.forEach(e -> result.add(table.putItem(e)) );

        return result;
    }
}
