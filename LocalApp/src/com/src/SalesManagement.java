package com.src;

import org.lightcouch.CouchDbClient;

import java.util.*;

/**
 * Created by talhaparacha on 21/05/2017.
 */
public class SalesManagement {
    static Scanner scanner = new Scanner(System.in);

    public final static void salesManagement() throws Exception
    {
        System.out.println("Press: \n 1 for creating a new sale");

        // ask for user input and move accordingly
        String input = scanner.nextLine();
        if (input.equals("1")) {
            newSaleScreen();
            System.out.println("Sucessfully done with the sale");
            HelperFunctions.waitForUserInput();
        }
        else {
            throw new Exception("ERROR: Invalid choice.");
        }
    }


    public final static void newSaleScreen() throws Exception
    {
        List<String> items = new LinkedList<String>();
        while (true) {
            System.out.print("Enter item NAME (press -1 to finalize order): ");
            String temp = scanner.nextLine();
            if (temp.equals("-1")) {
                break;
            }
            items.add(temp);
        }
        newSale(items);
    }

    public final static void newSale(List<String> itemNames) throws Exception
    {
        for (int i = 0; i < itemNames.size(); i++) {
            if (itemNames.get(i).isEmpty()) {
                throw new Exception("ERROR: item NAME cannot be empty");
            }
        }
        if (itemNames.size() == 0) {
            throw new Exception("ERROR: no item selected for sale");
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int month = Calendar.getInstance().get(Calendar.MONTH);

        // iterate for all items in the shopping cart
        CouchDbClient dbClient = CouchDBClient.getClient("sales");
        for (int i = 0; i < itemNames.size(); i++) {
            String itemName = itemNames.get(i);
            String price = ItemsManagement.getItemPrice(itemName);

            // add new sale to DB
            Map<String, Object> map = new HashMap<>();
            map.put("itemName", itemName.toLowerCase());
            map.put("saleAmount", price);
            map.put("shopName", Main.shopeName);
            map.put("year", year);
            map.put("day", day);
            map.put("month", month);
            dbClient.save(map);
        }

        dbClient.shutdown();

        int customerID = CustomerManagement.addOrder(itemNames);
        System.out.printf("The customer ID is: %d\n", customerID);
    }
}

