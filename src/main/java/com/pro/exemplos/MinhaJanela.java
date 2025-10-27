package com.pro.exemplos;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MinhaJanela extends JFrame {

	private static final long serialVersionUID = 1L;

	public MinhaJanela() {
		// Configurações da janela
		setTitle("Minha Janela Swing");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centraliza na tela

		// Cria painel para colocar componentes
		JPanel painel = new JPanel();

		// Cria botão
		JButton botao = new JButton("Clique aqui!");

		// Adiciona ação ao botão
		botao.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Hello, você clicou no botão!");
		});

		// Adiciona o botão ao painel
		painel.add(botao);

		// Adiciona o painel à janela
		add(painel);
	}

	public static void main(String[] args) {
		// Cria e mostra a janela
		javax.swing.SwingUtilities.invokeLater(() -> {
			new MinhaJanela().setVisible(true);
		});
	}
}