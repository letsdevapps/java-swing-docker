package com.pro.frame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MainContentFrameOLD extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Font fontArialPlain20 = new Font("Arial", Font.PLAIN, 20);
	private final Font fontArialPlain16 = new Font("Arial", Font.PLAIN, 16);

	private JTextArea textArea;

	private void appendOutput(String text) {
		SwingUtilities.invokeLater(() -> textArea.append(text));
	}

	public MainContentFrameOLD() {
		// Frame setup
		setTitle("Docker Manager");
		setSize(1024, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centraliza a janela

		// Menu Inicio
		JMenu menuInicio = new JMenu("Inicio");
		menuInicio.setFont(fontArialPlain20);
		
		JMenuItem menuItemDockerPs = new JMenuItem("Listar Instancias");
		menuItemDockerPs.setFont(fontArialPlain20);
		menuItemDockerPs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				doDockerPs();
			}
		});
		menuInicio.add(menuItemDockerPs);

		// Menu Bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuInicio);
		setJMenuBar(menuBar);

		// Painel principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Área de saída
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane, BorderLayout.CENTER);

		add(panel);
	}

	private void doDockerPs() {
		new Thread(() -> {
			appendOutput("docker ps\n\n");
			try {
	            ProcessBuilder pb = new ProcessBuilder("docker", "ps");

	            // Redireciona a saída padrão e a saída de erro
	            pb.redirectErrorStream(true);
	            Process process = pb.start();

	            // Lê toda a saída do processo
	            StringBuilder output = new StringBuilder();
	            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    output.append(line).append("\n");
	                    appendOutput(line + "\n");
	                }
	            }

	            int exitCode = process.waitFor();

	            // Verifica o resultado
	            if (exitCode == 0) {
	                appendOutput("\nListagem Docker realizada com sucesso!\n");
	            } else {
	                appendOutput("Erro ao iniciar a Listagem Docker. Código de saída: " + exitCode + "\n");
	                appendOutput("Detalhes do erro: " + output.toString() + "\n");
	                return;
	            }
	        } catch (IOException | InterruptedException ex) {
	            appendOutput("Erro: " + ex.getMessage() + "\n");
	        }
		}).start();
	}
}
