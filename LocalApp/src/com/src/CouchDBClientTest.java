package com.src;

import org.lightcouch.CouchDbClient;

import static org.junit.Assert.*;

/**
 * Created by talhaparacha on 21/05/2017.
 */
public class CouchDBClientTest {

    @org.junit.Test
    public void testDBsAccess() {
        CouchDbClient client = CouchDBClient.getClient("sales");
        client.shutdown();

        client = CouchDBClient.getClient("employees");
        client.shutdown();

        client = CouchDBClient.getClient("items");
        client.shutdown();
    }

}