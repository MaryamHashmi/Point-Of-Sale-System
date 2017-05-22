package com.src;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by talhaparacha on 21/05/2017.
 */
public class SalesManagementTest {
    @Test(expected = Exception.class)
    public void emptyCart() throws Exception {
        SalesManagement.newSale(new LinkedList<String>());
    }

    @Test(expected = Exception.class)
    public void invalidSaleItem() throws Exception {
        List<String> list = new LinkedList<String>();
        list.add("blahblahblah");
        SalesManagement.newSale(list);
    }
}