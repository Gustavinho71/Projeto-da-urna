package com.mycompany.model;


import com.mongodb.client.*;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

public class CandidatoDAO {

    private final MongoCollection<Document> collection;

    public CandidatoDAO() {
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        collection = db.getCollection("candidatos");
    }

    public Candidato buscarPorNumero(String numero) {
    try {
        Document doc = collection.find(eq("numero", numero)).first();
        if (doc == null) return null;

        String numeroCand = doc.getString("numero");
        String nome = doc.getString("nome");
        String caminhoImg = doc.getString("caminhoFoto"); 
        String nomePartido = doc.getString("partido");    

        Partido partido = new Partido(nomePartido);     

        Candidato candidato = new Candidato(nome, partido, numeroCand, caminhoImg);
        candidato.setVotos(doc.getInteger("votos", 0));

        return candidato;
    } catch (Exception e) {
        System.err.println("Erro ao buscar candidato: " + e.getMessage());
        return null;
    }
}


    public void incrementarVoto(String numero) {
        try {
            collection.updateOne(eq("numero", numero), new Document("$inc", new Document("votos", 1)));
        } catch (Exception e) {
            System.err.println("Erro ao incrementar voto: " + e.getMessage());
        }
    }

    public List<Candidato> listarTodos() {
    List<Candidato> lista = new ArrayList<>();
    try {
        for (Document doc : collection.find()) {
            String numero = doc.getString("numero");
            String nome = doc.getString("nome");
            String caminhoImg = doc.getString("caminhoFoto");
            String nomePartido = doc.getString("partido");

            Partido partido = new Partido(nomePartido);

            Candidato candidato = new Candidato(nome, partido, numero, caminhoImg);
            candidato.setVotos(doc.getInteger("votos", 0));

            lista.add(candidato);
        }
    } catch (Exception e) {
        System.err.println("Erro ao listar candidatos: " + e.getMessage());
    }
    return lista;
}

    public void zerarVotos() {
        try {
            collection.updateMany(new Document(), new Document("$set", new Document("votos", 0)));
        } catch (Exception e) {
            System.err.println("Erro ao zerar votos: " + e.getMessage());
        }
    }
}
