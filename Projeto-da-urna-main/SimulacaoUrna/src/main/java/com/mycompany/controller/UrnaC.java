/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

/**
 *
 * @author 232.976684
 */

import com.mycompany.model.CandidatoDAO;
import com.mycompany.view.Urna;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
        
public class UrnaC {
    
    private CandidatoDAO candidato;
    private StringBuilder numDigitado = new StringBuilder();
    private int maxDigitos = 5;
    private Urna urnaView;
    
    
//    public UrnaC(Urna view, CandidatoDAO candidato, StringBuilder numDigitado, int maxDigitos){
    public UrnaC(Urna urnaView) {
        this.urnaView = urnaView;
//        this.urnaView = view;
//        this.candidato = candidato;
//        this.numDigitado = numDigitado;
//        this.maxDigitos = maxDigitos;
        
        urnaView.getNum0().addActionListener(e -> System.out.println("botao 0 clicado"));
    }
    
    
}
