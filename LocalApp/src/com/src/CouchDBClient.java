package com.src;

import org.lightcouch.CouchDbClient;

class CouchDBClient{
    // settings
    static String username = "talha";
    static String password = "talha";
    static int port = 5984;
    static String address = "127.0.0.1";

    public static CouchDbClient getClient(String databaseName) {
        return new CouchDbClient(databaseName, true, "http", address, port, username, password);
    }
}