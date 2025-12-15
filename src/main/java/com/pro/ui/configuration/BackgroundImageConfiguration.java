package com.pro.ui.configuration;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BackgroundImageConfiguration {

    private JFrame jframe;

    private Image imagemFundo;

    public BackgroundImageConfiguration(JFrame jframe) {
        this.jframe = jframe;
    }

    public void autoSetUp() {

        try {
            imagemFundo = ImageIO.read(getClass().getResource("/static/img/docker/docker-logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagemFundo != null) {
                    g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // painel.setLayout(new FlowLayout()); // ou qualquer layout que desejar
        // painel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // layout com
        // espaçamento
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        // Adiciona botões
        painel.add(new JButton("Botão 1"));
        painel.add(new JButton("Botão 2"));

        // Adiciona diferentes componentes
        JButton botaoSimples = new JButton("Clique Aqui");
        JButton botaoEspecial = new JButton("Botão Especial");
        // JTextArea textArea = new JTextArea(5, 20);
        JCheckBox checkBox = new JCheckBox("Concordo com os termos");
        // String[] opcoes = {"Opção 1", "Opção 2", "Opção 3"};
        // JComboBox<String> comboBox = new JComboBox<>(opcoes);

        // Opcional: configurar o JTextArea
        // textArea.setLineWrap(true);
        // textArea.setWrapStyleWord(true);

        // Adiciona os componentes ao painel
        painel.add(botaoSimples);
        painel.add(botaoEspecial);
        // painel.add(new JScrollPane(textArea));
        painel.add(checkBox);
        // painel.add(comboBox);

        jframe.add(painel);
    }
}
