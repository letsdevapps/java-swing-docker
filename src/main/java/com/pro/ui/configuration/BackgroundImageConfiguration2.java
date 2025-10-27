package com.pro.ui.configuration;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BackgroundImageConfiguration2 {

	private JFrame jframe;
	
	private Image imagemFundo;

	public BackgroundImageConfiguration2(JFrame jframe) {
		this.jframe = jframe;
	}
	
	public void autoSetUp() {
        try {
        	imagemFundo = ImageIO.read(getClass().getResource("/static/img/docker/docker-logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Painel personalizado que desenha a imagem de fundo
        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagemFundo != null) {
                    // Ajusta a imagem ao tamanho do painel
                    g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Usa um layout BoxLayout na vertical
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        // Criando componentes
        JButton botaoSimples = new JButton("Clique Aqui");
        JButton botaoEspecial = new JButton("Botão Especial");
        JTextArea textArea = new JTextArea(5, 20);
        JCheckBox checkBox = new JCheckBox("Concordo com os termos");
        String[] opcoes = {"Opção 1", "Opção 2", "Opção 3"};
        JComboBox<String> comboBox = new JComboBox<>(opcoes);

        // Configurações do JTextArea
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Envolver componentes em painéis transparentes para centralizar
        painel.add(Box.createVerticalStrut(20));
        painel.add(wrapComponentInPanel(botaoSimples));
        painel.add(Box.createVerticalStrut(10));
        painel.add(wrapComponentInPanel(botaoEspecial));
        painel.add(Box.createVerticalStrut(10));
        painel.add(wrapComponentInPanel(new JScrollPane(textArea)));
        painel.add(Box.createVerticalStrut(10));
        painel.add(wrapComponentInPanel(checkBox));
        painel.add(Box.createVerticalStrut(10));
        painel.add(wrapComponentInPanel(comboBox));
        painel.add(Box.createVerticalGlue()); // empurra os componentes para cima

        // Para deixar a transparência, configurar os painéis internos
        for (Component c : painel.getComponents()) {
            if (c instanceof JPanel) {
                ((JPanel) c).setOpaque(false);
            }
        }

        jframe.add(painel);
	}
	
	// Método auxiliar para centralizar componentes
    private JPanel wrapComponentInPanel(JComponent comp) {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new FlowLayout(FlowLayout.CENTER));
        p.add(comp);
        return p;
    }

}
