/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.time.LocalDateTime;

/**
 *
 * @author Edu2
 */


public class Voto {
    public enum TipoVoto {
    NORMAL, BRANCO, NULO;
}
    
    private String numeroCandidato;  
    private TipoVoto tipoVoto;
    private LocalDateTime timestamp;

    public Voto(String numeroCandidato, TipoVoto tipoVoto) {
        this.numeroCandidato = numeroCandidato;
        this.tipoVoto = tipoVoto;
        this.timestamp = LocalDateTime.now();
    }

    // getters e setters
    public String getNumeroCandidato() { return numeroCandidato; }
    public void setNumeroCandidato(String numeroCandidato) { this.numeroCandidato = numeroCandidato; }

    public TipoVoto getTipoVoto() { return tipoVoto; }
    public void setTipoVoto(TipoVoto tipoVoto) { this.tipoVoto = tipoVoto; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}