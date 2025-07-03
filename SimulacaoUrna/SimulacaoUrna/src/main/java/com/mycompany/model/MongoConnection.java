/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author dejae
 */
public class MongoConnection {

    private static MongoConnection instance;
    private MongoClient client;
    private MongoDatabase database;

    MongoConnection() {
        client = MongoClients.create("mongodb://localhost:27017");
        database = client.getDatabase("urnaDB");
    }

    public static synchronized MongoConnection getInstance() {
        if (instance == null) {
            instance = new MongoConnection();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        if (client != null) {
            client.close();
        }
    }
}