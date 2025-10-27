package com.pro.exemplos;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MinhaJanelaCards extends JFrame {

	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
	private JPanel container;

	public MinhaJanelaCards() {
        setTitle("Exemplo de troca de painéis");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Gerenciador de layout
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // Criação de painéis
        JPanel painel1 = new JPanel();
        painel1.add(new JLabel("Painel 1"));
        JButton irParaPainel2 = new JButton("Ir para Painel 2");
        irParaPainel2.addActionListener(e -> cardLayout.show(container, "painel2"));
        painel1.add(irParaPainel2);

        JPanel painel2 = new JPanel();
        painel2.add(new JLabel("Painel 2"));
        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(e -> cardLayout.show(container, "painel1"));
        painel2.add(voltar);

        // Adiciona painéis ao container
        container.add(painel1, "painel1");
        container.add(painel2, "painel2");

        // Define o painel inicial
        cardLayout.show(container, "painel1");

        add(container);
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new MinhaJanelaCards().setVisible(true);
		});
	}
}
