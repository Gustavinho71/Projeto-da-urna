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
    
	private String sigla;
	private String numPrefixo;
	
	public Partido(String sigla, String numPrefixo) {
		super();
		this.sigla = sigla;
		this.numPrefixo = numPrefixo;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNumPrefixo() {
		return numPrefixo;
	}

	public void setNumPrefixo(String numPrefixo) {
		this.numPrefixo = numPrefixo;
	}
	
}
