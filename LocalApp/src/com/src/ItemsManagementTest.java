package com.src;

import static org.junit.Assert.*;

/**
 * Created by talhaparacha on 21/05/2017.
 */
public class ItemsManagementTest {

    @org.junit.Test
    public void addRemoveItem() throws Exception {
        int previous = ItemsManagement.getItemsPriceList().size();
        ItemsManagement.addNewItem("test-item", "10");
        assertEquals(previous + 1, ItemsManagement.getItemsPriceList().size());
        ItemsManagement.removeItem("test-item");
        assertEquals(previous, ItemsManagement.getItemsPriceList().size());
    }

    @org.junit.Test
    public void getItemPrice() throws Exception {
        ItemsManagement.addNewItem("test-item", "10");
        assertEquals("10", ItemsManagement.getItemPrice("test-item"));
        ItemsManagement.removeItem("test-item");
    }

    @org.junit.Test(expected = Exception.class)
    public void addInvalidItemPrice() throws Exception {
        ItemsManagement.addNewItem("burger", "1243wdad");
    }

    @org.junit.Test(expected = Exception.class)
    public void addInvalidItemName() throws Exception {
        ItemsManagement.addNewItem("", "1243");
    }


}