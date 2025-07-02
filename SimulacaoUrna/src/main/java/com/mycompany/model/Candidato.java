/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author dejae
 */
public class Candidato {
    private String nome;
    private Partido partido;   // agora objeto Partido
    private String numero;
    private String caminhoImg;
    private int votos;

    public Candidato() {}

    public Candidato(String nome, Partido partido, String numero, String caminhoImg) {
        this.nome = nome;
        this.partido = partido;
        this.numero = numero;
        this.caminhoImg = caminhoImg;
        this.votos = 0;
    }

    // getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Partido getPartido() { return partido; }
    public void setPartido(Partido partido) { this.partido = partido; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getCaminhoImg() { return caminhoImg; }
    public void setCaminhoImg(String caminhoImg) { this.caminhoImg = caminhoImg; }

    public int getVotos() { return votos; }
    public void setVotos(int votos) { this.votos = votos; }
}
