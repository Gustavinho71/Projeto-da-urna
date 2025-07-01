package com.mycompany.model;


import com.mongodb.client.*;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDAO {
    private final MongoCollection<Document> collection;

    public CandidatoDAO() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = client.getDatabase("urna");
        collection = database.getCollection("candidatos");
    }

    

    public Candidato buscarPorNumero(String numero) {
        Document doc = collection.find(eq("numero", numero)).first();
        if (doc == null) return null;

        Candidato candidato = new Candidato();
        candidato.setNumero(doc.getString("numero"));
        candidato.setNome(doc.getString("nome"));
        candidato.setPartido(doc.getString("partido"));
        //candidato.setCaminhoFoto(doc.getString("caminhoFoto"));
        candidato.setVotos(doc.getInteger("votos"));
        return candidato;
    }

    public void incrementarVoto(String numero) {
        collection.updateOne(eq("numero", numero), new Document("$inc", new Document("votos", 1)));
    }

    public List<Candidato> listarTodos() {
        List<Candidato> lista = new ArrayList<>();
        for (Document doc : collection.find()) {
            Candidato candidato = new Candidato();
            candidato.setNumero(doc.getString("numero"));
            candidato.setNome(doc.getString("nome"));
            candidato.setPartido(doc.getString("partido"));
            //candidato.setCaminhoFoto(doc.getString("caminhoFoto"));
            candidato.setVotos(doc.getInteger("votos"));
            lista.add(candidato);
        }
        return lista;
    }

    public void zerarVotos() {
        collection.updateMany(new Document(), new Document("$set", new Document("votos", 0)));
    }
}
