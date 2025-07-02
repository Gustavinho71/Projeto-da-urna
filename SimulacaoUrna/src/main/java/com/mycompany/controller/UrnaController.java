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
           // view.getLblPartidoCandidato().setText(candidato.getPartido());//
            view.getLabelStatusVoto().setText("Confirme seu voto.");
        } else {
            view.getLblNomeCandidato().setText("CANDIDATO INEXISTENTE");
            view.getLblPartidoCandidato().setText("");
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

    if (votoBranco) {
        votoDAO.registrarVoto(null, TipoVoto.BRANCO);
        votoConfirmado = true;
    } else {
        String numero = numeroDigitado.toString();
        Candidato candidato = candidatoDAO.buscarPorNumero(numero);

        if (candidato != null) {
            candidatoDAO.incrementarVoto(numero);
            votoDAO.registrarVoto(numero, TipoVoto.NORMAL);
        } else {
            votoDAO.registrarVoto(numero, TipoVoto.NULO);
        }
        votoConfirmado = true;
    }

    view.getLabelStatusVoto().setText("FIM. Seu voto foi registrado.");

    // Parar qualquer timer anterior
    if (resetTimer != null && resetTimer.isRunning()) {
        resetTimer.stop();
    }

    // Criar novo timer para resetar a tela
    resetTimer = new Timer(2000, e -> {
        resetarTela();
        resetTimer.stop(); // evitar múltiplas execuções
    });
    resetTimer.setRepeats(false); // garantir que só execute uma vez
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

}
