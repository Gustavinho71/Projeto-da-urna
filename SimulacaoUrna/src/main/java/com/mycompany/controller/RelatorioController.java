/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

/**
 *
 * @author dejae
 */
import com.mycompany.model.Candidato;
import com.mycompany.model.CandidatoDAO;
import com.mycompany.view.Relatorio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class RelatorioController {

    private final Relatorio view;
    private final CandidatoDAO candidatoDAO;

    public RelatorioController(Relatorio view) {
        this.view = view;
        this.candidatoDAO = new CandidatoDAO();

        carregarTabela();
        configurarBotaoFechar();
    }

    private void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) view.getTabelaRelatorio().getModel();
        modelo.setRowCount(0); // Limpa tabela

        List<Candidato> candidatos = candidatoDAO.listarTodos();
        int totalVotos = candidatos.stream().mapToInt(Candidato::getVotos).sum();

        for (Candidato c : candidatos) {
            double percentual = (totalVotos > 0)
                ? (c.getVotos() * 100.0 / totalVotos)
                : 0.0;

            modelo.addRow(new Object[]{
                c.getNumero(),
                c.getNome(),
                c.getPartido(),
                c.getVotos(),
                String.format("%.2f %%", percentual)
            });
        }
    }

    private void configurarBotaoFechar() {
        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose(); // Fecha a janela
            }
        });
    }
}
