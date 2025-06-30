/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author dejae
 */
public class CandidatoDAO {

    private String nome;
    private String partido;
    private String numero;
    private String caminhoImg;
    private int votos;

    public CandidatoDAO(String nome, String partido, String numero, String caminhoImg) {
        this.nome = nome;
        this.partido = partido;
        this.numero = numero;
        this.caminhoImg = caminhoImg;
        this.votos = 0; 
    }

    public String getNome() {
        return nome;
    }

   

    public String getPartido() {
        return partido;
    }

   

    public String getNumero() {
        return numero;
    }

   
    public String getCaminhoImg() {
        return caminhoImg;
    }

    public void setCaminhoImg(String caminhoImg) {
        this.caminhoImg = caminhoImg;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public String getInfoResumo() {
        return numero + " - " + nome + " (" + partido + ")"; //Usar isso na tela de relátorio!//
    }
}


 