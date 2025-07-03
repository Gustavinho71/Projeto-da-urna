/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Edu2
 */
public class PartidoDAO {

    private static final List<PartidoInfo> partidos = List.of(
        new PartidoInfo("91", "PEsp"),
        new PartidoInfo("92", "PMus"),
        new PartidoInfo("93", "PProf"),
        new PartidoInfo("94", "PFest"),
        new PartidoInfo("95", "PFolc")
    );

    private static class PartidoInfo {
        String prefixo;
        String nome;

        PartidoInfo(String prefixo, String nome) {
            this.prefixo = prefixo;
            this.nome = nome;
        }
    }

    public static Partido getPartidoPorNumeroCandidato(String numeroCandidato) {
        if (numeroCandidato == null || numeroCandidato.length() < 2) 
            return new Partido("Partido desconhecido");

        String prefixo = numeroCandidato.substring(0, 2);

        for (PartidoInfo info : partidos) {
            if (info.prefixo.equals(prefixo)) {
                return new Partido(info.nome);
            }
        }
        return new Partido("Partido desconhecido");
    }
}
