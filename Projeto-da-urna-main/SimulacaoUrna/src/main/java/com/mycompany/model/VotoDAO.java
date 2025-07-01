package com.mycompany.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author dejae
 */
public class VotoDAO {
    

    private final MongoCollection<Document> collection;

    public VotoDAO() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = client.getDatabase("urna");
        collection = db.getCollection("votos");
    }

    public void registrarVoto(String numeroCandidato) {
        Document voto = new Document("numero", numeroCandidato);
        collection.insertOne(voto);
    }
}
