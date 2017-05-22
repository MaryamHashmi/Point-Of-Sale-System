package com.src;

import com.google.gson.JsonObject;
import org.lightcouch.CouchDbClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class ItemsManagement {
    static Scanner scanner = new Scanner(System.in);

    public final static void itemsManagement() throws Exception
    {
        System.out.println("Press: \n 1 for viewing all items list \n 2 for adding a new item\n 3 for removing an item");

        // ask for user input and move accordingly
        String input = scanner.nextLine();
        if (input.equals("1")) {
            viewAllItems();
        } else if (input.equals("2")) {
            addNewItemScreen();
            System.out.println("Sucessfully done adding the new item to inventory");
            HelperFunctions.waitForUserInput();
        }
        else if (input.equals("3")) {
            removeItemScreen();
            System.out.println("Sucessfully done removing the item from inventory");
            HelperFunctions.waitForUserInput();
        }
        else {
            throw new Exception("ERROR: Invalid choice.");
        }
    }

    public final static void addNewItemScreen() throws Exception {
        System.out.print("Enter item NAME: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter item PRICE: ");
        String price = scanner.nextLine();
        addNewItem(itemName, price);
    }

    public final static void addNewItem(String name, String price) throws Exception
    {
        try {
            int itemPrice = Integer.parseInt(price);
        } catch(Exception e) {
            try {
                float itemPrice = Float.parseFloat(price);
            } catch (Exception e2) {
                throw new Exception("ERROR: Item price must be a number");
            }
        }
        if (name.isEmpty() || price.isEmpty()) {
            throw new Exception("ERROR: item NAME / PRICE cannot be empty");
        }
        CouchDbClient dbClient = CouchDBClient.getClient("items");
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.toLowerCase());
        map.put("price", price);
        dbClient.save(map);
        dbClient.shutdown();
    }

    public final static void removeItemScreen() throws Exception {
        System.out.print("Enter item NAME which you want to remove: ");
        String itemName = scanner.nextLine().toLowerCase();
        if (itemName.isEmpty()) {
            throw new Exception("ERROR: item NAME / PRICE cannot be empty");
        }
        removeItem(itemName);
    }

    public final static void removeItem(String itemName) throws Exception {
        CouchDbClient client = CouchDBClient.getClient("items");
        List<JsonObject> list = client.view("myDesignDoc/item-price").key(itemName).includeDocs(true).query(JsonObject.class);
        if (!list.isEmpty()) {
            String itemId = list.get(0).get("_id").getAsString();
            String itemRev = list.get(0).get("_rev").getAsString();
            client.remove(itemId, itemRev);
        }
        client.shutdown();
    }

    public final static void viewAllItems() throws Exception
    {
        System.out.println("Here is the list of all items available with this Caffe:");
        List<JsonObject> allDocs = getItemsPriceList();
        for (int i = 0; i < allDocs.size(); i++) {
            JsonObject temp = allDocs.get(i);
            System.out.printf("Name: %s \nPrice in USD: %s\n\n", temp.get("key").getAsString().toUpperCase(), temp.get("value").getAsString().toUpperCase());
        }
        HelperFunctions.waitForUserInput();
    }

    public final static List<JsonObject> getItemsPriceList() throws Exception {
        CouchDbClient dbClient = CouchDBClient.getClient("items");
        List<JsonObject> list = dbClient.view("myDesignDoc/item-price").query(JsonObject.class);
        dbClient.shutdown();
        return list;
    }

    public final static String getItemPrice(String itemName) throws Exception {
        CouchDbClient dbClient = CouchDBClient.getClient("items");
        List<JsonObject> list = dbClient.view("myDesignDoc/item-price").key(itemName).query(JsonObject.class);
        if (list.size() == 0) {
            throw new Exception("ERROR: Item does not exist in the inventory");
        }
        String price = list.get(0).get("value").getAsString();
        dbClient.shutdown();
        return price;
    }
}