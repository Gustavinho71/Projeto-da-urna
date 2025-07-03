package com.mycompany.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            doc.append("tipo", voto.getTipoVoto().name());
            doc.append("timestamp", voto.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            collection.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Erro ao registrar voto: " + e.getMessage());
        }
    }

  
    public void registrarVoto(String numero, Voto.TipoVoto tipoVoto) {
        try {
            Document doc = new Document();
            doc.append("numero", numero);
            doc.append("tipo", tipoVoto.name());
            doc.append("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            collection.insertOne(doc);
        } catch (Exception e) {
            System.err.println("Erro ao registrar voto: " + e.getMessage());
        }
    }

 
    public int contarVotosBrancos() {
        try {
            return (int) collection.countDocuments(new Document("tipo", "BRANCO"));
        } catch (Exception e) {
            System.err.println("Erro ao contar votos brancos: " + e.getMessage());
            return 0;
        }
    }


    public int contarVotosNulos() {
        try {
            return (int) collection.countDocuments(new Document("tipo", "NULO"));
        } catch (Exception e) {
            System.err.println("Erro ao contar votos nulos: " + e.getMessage());
            return 0;
        }
    }
    
    public void limparVotos() {
    try {
        collection.deleteMany(new Document()); 
    } catch (Exception e) {
        System.err.println("Erro ao limpar votos: " + e.getMessage());
    }
}

}
