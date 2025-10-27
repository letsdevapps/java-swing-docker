package com.pro.exemplos;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MinhaJanelaTopmenu extends JFrame {

	private static final long serialVersionUID = 1L;

	public MinhaJanelaTopmenu() {
		// Configurações iniciais da janela
		setTitle("Exemplo de Menu Superior");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centraliza a janela

		// Cria o menu
		JMenuBar menuBar = new JMenuBar();

		// Cria o menu "Arquivo"
		JMenu menuArquivo = new JMenu("Arquivo");
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuArquivo.add(sair);

		// Cria o menu "Opções"
		JMenu menuOpcoes = new JMenu("Opções");
		JMenuItem opcao1 = new JMenuItem("Opção 1");
		opcao1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Aqui, você adicionaria a lógica para a ação da opção 1.
				// Por exemplo, atualizar o painel principal.
				atualizarPainelPrincipal("Opção 1 selecionada.");
			}
		});
		menuOpcoes.add(opcao1);

		JMenuItem opcao2 = new JMenuItem("Opção 2");
		opcao2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarPainelPrincipal("Opção 2 selecionada.");
			}
		});
		menuOpcoes.add(opcao2);

		// Adiciona os menus à barra de menus
		menuBar.add(menuArquivo);
		menuBar.add(menuOpcoes);

		// Cria o painel principal
		JPanel painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new FlowLayout());
		painelPrincipal.add(new JLabel("Conteúdo inicial"));
		setContentPane(painelPrincipal);

		// Define a barra de menus na janela
		setJMenuBar(menuBar);

		setVisible(true);
	}

	// Método para atualizar o painel principal
	private void atualizarPainelPrincipal(String mensagem) {
		JPanel painelPrincipal = (JPanel) getContentPane(); // Recupera o painel principal
		painelPrincipal.removeAll(); // Limpa o conteúdo anterior
		painelPrincipal.add(new JLabel(mensagem));
		painelPrincipal.revalidate(); // Atualiza a visualização
		painelPrincipal.repaint(); // Redesenha o painel
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MinhaJanelaTopmenu();
			}
		});
	}
}