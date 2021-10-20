package com.aws.demo.dynamodb.util;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Util class to read json file
 */
public class JsonUtils {

    public static List<Item> dynamoDBItems(JsonNode root) throws IOException {

        List<Item> items = new ArrayList<>();

        if(root.isArray()) {
            Iterator<JsonNode> iterator = root.iterator();

            while(iterator.hasNext()) {
                JsonNode node = iterator.next();
                items.add(itemFromNode(node));
            }
        }

        return items;
    }

    private static Item itemFromNode(JsonNode node) {
        int year = node.get("year").asInt();
        String title = node.get("title").asText();
        String rest = node.get("info").toString();
        Item item = new Item()
                .withPrimaryKey("year",year,"title",title)
                .with("info", rest);
        return item;
    }
}
