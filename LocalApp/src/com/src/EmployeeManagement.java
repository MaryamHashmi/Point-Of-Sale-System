package com.src;

import com.google.gson.JsonObject;
import org.lightcouch.CouchDbClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class EmployeeManagement {
    static Scanner scanner = new Scanner(System.in);

    public final static void employeeManagement() throws Exception
    {
        System.out.println("Press: \n 1 for viewing all employees list \n 2 for adding a new employee\n 3 for removing an employee");

        // ask for user input and move accordingly
        String input = scanner.nextLine();
        if (input.equals("1")) {
            viewAllEmployees();
        } else if (input.equals("2")) {
            addNewEmployeeScreen();
            System.out.println("Sucessfully done adding the new employee");
            HelperFunctions.waitForUserInput();
        }
        else if (input.equals("3")) {
            removeEmployeeScreen();
            System.out.println("Sucessfully done removing the employee");
            HelperFunctions.waitForUserInput();
        }
        else {
            throw new Exception("ERROR: Invalid choice.");
        }
    }

    public final static void viewAllEmployees() throws Exception
    {
        System.out.println("Here is the list of all employees associated with this Caffe:");
        List<JsonObject> allDocs = getEmployeesJobsList();
        for (int i = 0; i < allDocs.size(); i++) {
            JsonObject temp = allDocs.get(i);
            System.out.printf("Name: %s \nJob Title with Employee ID: %s\n\n", temp.get("key").getAsString().toUpperCase(), temp.get("value").getAsString().toUpperCase());
        }
        HelperFunctions.waitForUserInput();
    }

    public final static void addNewEmployeeScreen() throws Exception {
        System.out.print("Enter employee NAME: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee JOB: ");
        String job = scanner.nextLine();
        System.out.print("Enter employee SERIAL ID: ");
        String sID = scanner.nextLine();
        addNewEmployee(name, job, sID);
    }

    public final static void addNewEmployee(String name, String job, String serial) throws Exception
    {
        if (job.isEmpty() || name.isEmpty() || serial.isEmpty()) {
            throw new Exception("ERROR: employee NAME / JOB cannot be empty");
        }
        CouchDbClient dbClient = CouchDBClient.getClient("employees");
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.toLowerCase());
        map.put("job", job.toLowerCase());
        map.put("serial", serial);
        dbClient.save(map);
        dbClient.shutdown();
    }

    public final static void removeEmployeeScreen() throws Exception {
        System.out.print("Enter employee SERIAL ID which you want to remove: ");
        String sID = scanner.nextLine().toLowerCase();
        if (sID.isEmpty()) {
            throw new Exception("ERROR: employee SERIAL ID cannot be empty");
        }
        removeEmployee(sID);
    }

    public final static void removeEmployee(String serialID) throws Exception {
        CouchDbClient client = CouchDBClient.getClient("employees");
        List<JsonObject> list = client.view("myDesignDoc/serial").key(serialID).includeDocs(true).query(JsonObject.class);
        if (!list.isEmpty()) {
            String itemId = list.get(0).get("_id").getAsString();
            String itemRev = list.get(0).get("_rev").getAsString();
            client.remove(itemId, itemRev);
        }
        client.shutdown();
    }

    public final static List<JsonObject> getEmployeesJobsList() throws Exception {
        CouchDbClient dbClient = CouchDBClient.getClient("employees");
        List<JsonObject> list = dbClient.view("myDesignDoc/employee-job").query(JsonObject.class);
        dbClient.shutdown();
        return list;
    }
}