/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author dejae
 */
public class Partido {
     private String prefixo; 
    private String nome;    

    public Partido(String prefixo, String nome) {
        this.prefixo = prefixo;
        this.nome = nome;
    }

    public String getPrefixo() {
        
        return prefixo; 
    }
    
    public String getNome() {
        return nome;
    }
}
