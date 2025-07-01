/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.util.List;

/**
 *
 * @author Edu2
 */
public class PartidoDAO {
    
    public class PartidoService {
    private static final List<Partido> partidos = List.of(
        new Partido("91", "PEsp"),
        new Partido("92", "PMus"),
        new Partido("93", "PProf"),
        new Partido("94", "PFest"),
        new Partido("95", "PFolc")
    );

    public static String getNomePorPrefixo(String prefixo) {
        for (Partido p : partidos) {
            if (p.getPrefixo().equals(prefixo)) {
                return p.getNome();
            }
        }
        return "Partido desconhecido";
    }
}

    
}
