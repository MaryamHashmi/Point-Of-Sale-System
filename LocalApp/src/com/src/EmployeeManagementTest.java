package com.src;

import static org.junit.Assert.*;

/**
 * Created by talhaparacha on 21/05/2017.
 */
public class EmployeeManagementTest {

    @org.junit.Test
    public void addRemoveEmployee() throws Exception {
        int previousSize = EmployeeManagement.getEmployeesJobsList().size();
        EmployeeManagement.addNewEmployee("test-emp", "sales", "000000");

        int currentSize = EmployeeManagement.getEmployeesJobsList().size();
        assertEquals(previousSize + 1, currentSize);
        EmployeeManagement.removeEmployee("000000");

        currentSize = EmployeeManagement.getEmployeesJobsList().size();
        assertEquals(previousSize, currentSize);
    }

    @org.junit.Test(expected = Exception.class)
    public void addEmployeeWithoutID() throws Exception {
        EmployeeManagement.addNewEmployee("test-emp", "sales", "");
    }

    @org.junit.Test(expected = Exception.class)
    public void addEmployeeWithoutName() throws Exception {
        EmployeeManagement.addNewEmployee("", "sales", "000000");
    }

    @org.junit.Test(expected = Exception.class)
    public void addEmployeeWithoutDept() throws Exception {
        EmployeeManagement.addNewEmployee("test-emp", "", "000000");
    }
}