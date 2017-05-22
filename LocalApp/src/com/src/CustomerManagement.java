package com.src;

import com.google.gson.JsonObject;
import org.lightcouch.CouchDbClient;

import java.util.*;

public class CustomerManagement {

    private static Scanner scn = new Scanner(System.in);

    public final static void customerManagement() throws Exception
    {
        System.out.println("Press: \n 1 for viewing pending orders \n 2 for completing an order");

        // ask for user input and move accordingly
        String input = scn.nextLine();
        if (input.equals("1")) {
            viewPendingOrders();
        }
        else if (input.equals("2")) {
            completeOrderScreen();
            System.out.println("Sucessfully done completing the order");
            HelperFunctions.waitForUserInput();
        }
        else {
            throw new Exception("ERROR: Invalid choice.");
        }
    }

    public final static void viewPendingOrders() throws Exception {
        System.out.println("Here is the list of all orders currently pending:");
        CouchDbClient dbClient = CouchDBClient.getClient("orders");
        List<JsonObject> allDocs = dbClient.view("myDesignDoc/pending-orders").query(JsonObject.class);
        dbClient.shutdown();
        for (int i = 0; i < allDocs.size(); i++) {
            JsonObject temp = allDocs.get(i);
            System.out.printf("Customer: %s \nOrder Info: %s\n\n", temp.get("key").getAsString().toUpperCase(), temp.get("value").getAsString().toUpperCase());
        }
        HelperFunctions.waitForUserInput();
    }

    public final static void completeOrderScreen() throws Exception {
        System.out.print("Enter CUSTOMER: ");
        String id = scn.nextLine();
        completeOrder(id);
    }

    public final static void completeOrder(String id) throws Exception
    {
        if (id.isEmpty()) {
            throw new Exception("ERROR: customer ID invalid");
        }
        CouchDbClient dbClient = CouchDBClient.getClient("orders");
        List<JsonObject> list = dbClient.view("myDesignDoc/pending-orders").key(Integer.parseInt(id)).includeDocs(true).query(JsonObject.class);
        if (!list.isEmpty()) {
            JsonObject temp = list.get(0);
            temp.addProperty("status","ready");
            dbClient.update(temp);
        }
        dbClient.shutdown();
    }

    public final static int addOrder(List<String> items) throws Exception {
        Random ran = new Random();
        String result = String.join(", ", items);
        int customer = ran.nextInt(999999);
        CouchDbClient dbClient = CouchDBClient.getClient("orders");
        Map<String, Object> map = new HashMap<>();
        map.put("status", "pending");
        map.put("items", result);
        map.put("customer", customer);
        dbClient.save(map);
        return customer;
    }

}
