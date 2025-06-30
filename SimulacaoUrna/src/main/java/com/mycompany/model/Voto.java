package com.mycompany.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author dejae
 */
public class Voto {
    
	private CandidatoDAO candidato;
	private boolean branco;
	private boolean nulo;
	
	
	public Voto(CandidatoDAO candidato, boolean branco, boolean nulo) {
		super();
		this.candidato = candidato;
		this.branco = branco;
		this.nulo = nulo;
	}


	public CandidatoDAO getCandidato() {
		return candidato;
	}


	public void setCandidato(CandidatoDAO candidato) {
		this.candidato = candidato;
	}


	public boolean isBranco() {
		return branco;
	}


	public void setBranco(boolean branco) {
		this.branco = branco;
	}


	public boolean isNulo() {
		return nulo;
	}


	public void setNulo(boolean nulo) {
		this.nulo = nulo;
	}
	 
	 
	
}
