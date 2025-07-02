package com.mycompany.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.time.format.DateTimeFormatter;
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
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        collection = db.getCollection("votos");
    }

    public void registrarVoto(Voto voto) {
        try {
            Document doc = new Document();
            doc.append("numero", voto.getNumeroCandidato());
            doc.append("tipoVoto", voto.getTipoVoto().name());
            doc.append("timestamp", voto.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            collection.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Erro ao registrar voto: " + e.getMessage());
        }
    }

    public void registrarVoto(Object object, Voto.TipoVoto tipoVoto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

