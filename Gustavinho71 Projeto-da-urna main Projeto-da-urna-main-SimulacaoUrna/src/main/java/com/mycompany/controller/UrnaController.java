/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.mycompany.model.Candidato;
import com.mycompany.model.Partido;
import com.mycompany.model.Voto;
import com.mycompany.model.CandidatoDAO;
import com.mycompany.model.PartidoDAO;
import com.mycompany.model.Voto.TipoVoto;
import com.mycompany.model.VotoDAO;
import com.mycompany.view.Urna;
import javax.swing.ImageIcon;
import java.util.List;
import javax.swing.Timer;
import org.bson.Document;


/**
 *
 * @author dejae
 */
public class UrnaController {
    private Timer resetTimer;
    private final Urna view;
    private final CandidatoDAO candidatoDAO;
    private final VotoDAO votoDAO;

    private StringBuilder numeroDigitado = new StringBuilder();
    private boolean votoConfirmado = false;
    private boolean votoBranco = false;

    public UrnaController(Urna view) {
        this.view = view;
        this.candidatoDAO = new CandidatoDAO();
        this.votoDAO = new VotoDAO();

        registrarAcoes();
        resetarTela();
    }

    private void registrarAcoes() {
        view.getBtnNum0().addActionListener(e -> digitarNumero("0"));
        view.getBtnNum1().addActionListener(e -> digitarNumero("1"));
        view.getBtnNum2().addActionListener(e -> digitarNumero("2"));
        view.getBtnNum3().addActionListener(e -> digitarNumero("3"));
        view.getBtnNum4().addActionListener(e -> digitarNumero("4"));
        view.getBtnNum5().addActionListener(e -> digitarNumero("5"));
        view.getBtnNum6().addActionListener(e -> digitarNumero("6"));
        view.getBtnNum7().addActionListener(e -> digitarNumero("7"));
        view.getBtnNum8().addActionListener(e -> digitarNumero("8"));
        view.getBtnNum9().addActionListener(e -> digitarNumero("9"));

        view.getBtnCorrige().addActionListener(e -> resetarTela());
        view.getBtnBranco().addActionListener(e -> votoBranco());
        view.getBtnConfirma().addActionListener(e -> confirmarVoto());
    }

    private void digitarNumero(String numero) {
        if (votoConfirmado || votoBranco || numeroDigitado.length() >= 5) return;

        numeroDigitado.append(numero);
        atualizarCamposNumero();

        if (numeroDigitado.length() == 5) {
            exibirCandidato(numeroDigitado.toString());
        }
        
        if (numeroDigitado.length() == 5) {
    String numeroCompleto = numeroDigitado.toString();
    if ("99999".equals(numeroCompleto)) {
        abrirTelaRelatorio();
    } else {
        exibirCandidato(numeroCompleto);
    }
}
        

    }

    private void atualizarCamposNumero() {
        JLabel[] campos = {
            view.getLabelPosicao0(), view.getLabelPosicao1(), view.getLabelPosicao2(),
            view.getLabelPosicao3(), view.getLabelPosicao4()
        };

        for (int i = 0; i < campos.length; i++) {
            campos[i].setText(i < numeroDigitado.length() ? String.valueOf(numeroDigitado.charAt(i)) : "");
        }
    }

    private void exibirCandidato(String numero) {
    Candidato candidato = candidatoDAO.buscarPorNumero(numero);

    if (candidato != null) {
        view.getLblNomeCandidato().setText(candidato.getNome());

        if (candidato.getPartido() != null) {
            view.getLblPartidoCandidato().setText(candidato.getPartido().getNomePartido());
        } else {
            view.getLblPartidoCandidato().setText("Partido não informado");
        }

        String caminhoFoto = candidato.getCaminhoImg(); 
        if (caminhoFoto != null && !caminhoFoto.isEmpty()) {
            java.net.URL imgURL = getClass().getClassLoader().getResource("imagens/" + caminhoFoto);
            if (imgURL != null) {
                view.getLblCandidatoImg().setIcon(new javax.swing.ImageIcon(imgURL));
            } else {
                view.getLblCandidatoImg().setIcon(null);
            }
        } else {
            view.getLblCandidatoImg().setIcon(null);
        }

        view.getLabelStatusVoto().setText("Confirme seu voto.");
    } else {
        view.getLblNomeCandidato().setText("CANDIDATO INEXISTENTE");
        view.getLblPartidoCandidato().setText("");
        view.getLblCandidatoImg().setIcon(null);
        view.getLabelStatusVoto().setText("Voto Nulo. Confirme para continuar.");
    }
}

    private void votoBranco() {
        if (votoConfirmado || numeroDigitado.length() > 0) return;

        votoBranco = true;
        view.getLabelStatusVoto().setText("Voto em branco. Confirme para continuar.");
        limparCamposNumericos();
    }

   private void confirmarVoto() {
    if (votoConfirmado) return; 

    String numero = numeroDigitado.toString();
    Candidato candidato = candidatoDAO.buscarPorNumero(numero);

    if (votoBranco) {
      
        votoDAO.registrarVoto(null, Voto.TipoVoto.BRANCO);
        votoConfirmado = true;
        view.getLabelStatusVoto().setText("Voto em BRANCO registrado!");
    } else if (candidato != null) {
       
        candidatoDAO.incrementarVoto(numero);
        votoDAO.registrarVoto(numero, Voto.TipoVoto.NORMAL);
        votoConfirmado = true;
        view.getLabelStatusVoto().setText("Voto em " + candidato.getNome() + " registrado!");
    } else {
   
        votoDAO.registrarVoto(numero, Voto.TipoVoto.NULO);
        votoConfirmado = true;
        view.getLabelStatusVoto().setText("VOTO NULO registrado!");
    }

   
    if (resetTimer != null && resetTimer.isRunning()) {
        resetTimer.stop();
    }
    resetTimer = new Timer(2000, e -> {
        resetarTela();
        resetTimer.stop();
    });
    resetTimer.setRepeats(false);
    resetTimer.start();
}

 
    private void limparCamposNumericos() {
        view.getLabelPosicao0().setText("");
        view.getLabelPosicao1().setText("");
        view.getLabelPosicao2().setText("");
        view.getLabelPosicao3().setText("");
        view.getLabelPosicao4().setText("");
    }

   public void resetarTela() {
    if (resetTimer != null && resetTimer.isRunning()) {
        resetTimer.stop();
    }

    votoConfirmado = false;
    votoBranco = false;
    numeroDigitado.setLength(0);

    limparCamposNumericos();
    view.getLblNomeCandidato().setText("");
    view.getLblPartidoCandidato().setText("");
    view.getLblCandidatoImg().setIcon(null);
    view.getLabelStatusVoto().setText("Digite seu voto.");
}
   
   private void abrirTelaRelatorio() {
    List<Candidato> candidatos = candidatoDAO.listarTodos();
    int totalVotos = 0;

   
    for (Candidato c : candidatos) {
        totalVotos += c.getVotos();
    }

    int votosBrancos = votoDAO.contarVotosBrancos();
    int votosNulos = votoDAO.contarVotosNulos();
    totalVotos += votosBrancos + votosNulos;

    if (totalVotos == 0) {
        JOptionPane.showMessageDialog(view, "Nenhum voto registrado ainda.", "Relatório", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    
    String[] colunas = {"Nome", "Partido", "Votos", "Percentual"};
    Object[][] dados = new Object[candidatos.size() + 2][4]; 

    int i = 0;
    for (Candidato c : candidatos) {
        double perc = (c.getVotos() * 100.0) / totalVotos;
        dados[i][0] = c.getNome();
        dados[i][1] = c.getPartido() != null ? c.getPartido().getNomePartido() : "Sem partido";
        dados[i][2] = c.getVotos();
        dados[i][3] = String.format("%.2f%%", perc);
        i++;
    }

  
    dados[i][0] = "Votos Brancos";
    dados[i][1] = "-";
    dados[i][2] = votosBrancos;
    dados[i][3] = String.format("%.2f%%", (votosBrancos * 100.0) / totalVotos);

    
    dados[i + 1][0] = "Votos Nulos";
    dados[i + 1][1] = "-";
    dados[i + 1][2] = votosNulos;
    dados[i + 1][3] = String.format("%.2f%%", (votosNulos * 100.0) / totalVotos);

    javax.swing.JTable tabela = new javax.swing.JTable(dados, colunas);
    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(tabela);

    javax.swing.JDialog dialog = new javax.swing.JDialog(view, "Relatório de Votação", true);
    dialog.getContentPane().add(scrollPane);
    dialog.setSize(600, 400);
    dialog.setLocationRelativeTo(view);
    dialog.setVisible(true);

    resetarTela();
    
    candidatoDAO.zerarVotos(); 
votoDAO.limparVotos();    

JOptionPane.showMessageDialog(view, "Todos os votos foram zerados. Nova eleição iniciada!", "Reset", JOptionPane.INFORMATION_MESSAGE);

resetarTela();
}


}
