package com.pro.ui.configuration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.pro.ui.font.FontTools;

public class PanelConfiguration {

	private JFrame jframe;
	private JLabel jlabel;
	
	
	
	/*paginação*/
	private JTable tabela;
    private DefaultTableModel modelo;
    private List<Object[]> dadosCompletos;
    private int paginaAtual = 1;
    private int linhasPorPagina = 10;
    private int totalPaginas;
	
	

	public PanelConfiguration() {
	}

	public PanelConfiguration(JFrame jframe) {
		this.jframe = jframe;
	}

	public void autoSetUp() {
		jlabel = new JLabel("Bem-Vindos ao Docker Manager");
		jlabel.setForeground(Color.WHITE);
		jlabel.setHorizontalAlignment(SwingConstants.CENTER);
		jlabel.setFont(FontTools.getFontArialPlain40());

		ImageIcon icon = new ImageIcon(getClass().getResource("/static/img/docker/docker-logo.png"));
		Image iconResize = icon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
		JLabel iconLabel = new JLabel(new ImageIcon(iconResize));

		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.DARK_GRAY);
		//panel.setLayout(new BorderLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Configurações globais do GridBagConstraints
		// gbc.fill = GridBagConstraints.NONE; // Não preencher toda a célula
		// gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre componentes
		// gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(jlabel, gbc);
		//panel.add(jlabel, BorderLayout.CENTER);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(iconLabel, gbc);

		jframe.add(panel);
		//jframe.setContentPane(panel);
		
		
		
		
		/*
		// Lista do backend
		List<Pessoa> listaPessoas = Arrays.asList(
		    new Pessoa("João", 30, "joao@example.com"),
		    new Pessoa("Maria", 25, "maria@example.com"),
		    new Pessoa("Pedro", 40, "pedro@example.com")
		);

		// Preparar os dados para o JTable
		String[] colunas = {"Nome", "Idade", "Email"};
		Object[][] dados = new Object[listaPessoas.size()][colunas.length];

		for (int i = 0; i < listaPessoas.size(); i++) {
		    Pessoa p = listaPessoas.get(i);
		    dados[i][0] = p.nome;
		    dados[i][1] = p.idade;
		    dados[i][2] = p.email;
		}

		// Criar o JTable
		JTable tabela = new JTable(dados, colunas);

		// Opcional: ajustar tamanhos, editar, etc.
		//tabela.setFillsViewportHeight(true);

		// Adicionar em um JScrollPane
		JScrollPane scrollPane = new JScrollPane(tabela);
		//jframe.add(scrollPane);
		///
		 */
		
		
	    
	 // Dados fictícios
        dadosCompletos = new ArrayList<>();
        for (int i = 1; i <= 95; i++) {
            dadosCompletos.add(new Object[]{"Nome " + i, i, "email" + i + "@exemplo.com"});
        }

        // Calcula total de páginas
        totalPaginas = (int) Math.ceil((double) dadosCompletos.size() / linhasPorPagina);

        // Colunas
        String[] colunas = {"Nome", "Idade", "Email"};

        // Modelo inicial
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(colunas);

        tabela = new JTable(modelo);
        carregarPagina();

        JButton btnAnterior = new JButton("Anterior");
        JButton btnProximo = new JButton("Próximo");
        JLabel lblPagina = new JLabel();

        btnAnterior.addActionListener(e -> {
            if (paginaAtual > 1) {
                paginaAtual--;
                carregarPagina();
                lblPagina.setText("Página " + paginaAtual + " de " + totalPaginas);
            }
        });

        btnProximo.addActionListener(e -> {
            if (paginaAtual < totalPaginas) {
                paginaAtual++;
                carregarPagina();
                lblPagina.setText("Página " + paginaAtual + " de " + totalPaginas);
            }
        });

        lblPagina.setText("Página " + paginaAtual + " de " + totalPaginas);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAnterior);
        painelBotoes.add(btnProximo);
        painelBotoes.add(lblPagina);

        //jframe.add(new JScrollPane(tabela), BorderLayout.CENTER);
        //jframe.add(painelBotoes, BorderLayout.SOUTH);
	}
	
	private void carregarPagina() {
        // Limpa a tabela
        modelo.setRowCount(0);
        int start = (paginaAtual - 1) * linhasPorPagina;
        int end = Math.min(start + linhasPorPagina, dadosCompletos.size());
        for (int i = start; i < end; i++) {
            modelo.addRow(dadosCompletos.get(i));
        }
    }
}
