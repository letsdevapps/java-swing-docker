package com.pro.ui.configuration;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.pro.ui.font.FontTools;

public class MenuConfiguration {

	private JFrame jframe;
	private JMenuBar jmenuBar;

	public MenuConfiguration(JFrame jframe) {
		this.jframe = jframe;
	}

	public void autoSetUp() {
		jmenuBar = new JMenuBar();
		setupMenuBar();
		jframe.setJMenuBar(jmenuBar);
	}

	private JMenuBar setupMenuBar() {
		jmenuBar.add(setupMenuHome());
		jmenuBar.add(setupMenuServices());
		jmenuBar.add(setupMenuOptions());
		jmenuBar.add(setupMenuAbout());
		return jmenuBar;
	}

	private JMenu setupMenuHome() {
		JMenu menuHome = new JMenu("Arquivo");
		menuHome.setFont(FontTools.getFontArialPlain20());

		// Criando o item do menu
		JMenuItem helloItem = new JMenuItem("Say Hello");

		// Adicionando ação ao item
		helloItem.addActionListener(e -> {
			JOptionPane.showMessageDialog(jframe, "Hello World");
		});
		menuHome.add(helloItem);
		menuHome.addSeparator();
		menuHome.add(close());

		// Criando um botão
		JButton botao = new JButton("Clique Aqui");
		// Adicionando ação ao botão
		botao.addActionListener(e -> {
			JOptionPane.showMessageDialog(jframe, "Hello World");
		});

		// Adicionando o botão na JMenuBar
		// jmenuBar.add(botao);

		// jmenuBar.add(helloItem);

		return menuHome;
	}

	private JMenuItem close() {
		JMenuItem item = new JMenuItem("Sair");
		item.setFont(FontTools.getFontArialPlain20());
		item.addActionListener(e -> {
			System.exit(0);
		});
		return item;
	}

	private JMenu setupMenuServices() {
		JMenu menuServices = new JMenu("Serviços");
		menuServices.setFont(FontTools.getFontArialPlain20());
		return menuServices;
	}

	private JMenu setupMenuOptions() {
		JMenu menuOptions = new JMenu("Opções");
		menuOptions.setFont(FontTools.getFontArialPlain20());
		menuOptions.add(locale());
		menuOptions.add(resolution());
		return menuOptions;
	}

	private JMenu resolution() {
		JMenu menu = new JMenu("Resolução");
		menu.setFont(FontTools.getFontArialPlain20());

		JMenuItem res800x600 = new JMenuItem("800 x 600");
		res800x600.setFont(FontTools.getFontArialPlain20());
        res800x600.addActionListener(e -> {
        	jframe.setSize(800, 600);
        	jframe.setLocationRelativeTo(null);
		});

        JMenuItem res1024x768 = new JMenuItem("1024 x 768");
		res1024x768.setFont(FontTools.getFontArialPlain20());
        res1024x768.addActionListener(e -> {
        	jframe.setSize(1024, 768);
        	jframe.setLocationRelativeTo(null);
		});

		JMenuItem fullscreen = new JMenuItem("Tela Cheia");
        fullscreen.setFont(FontTools.getFontArialPlain20());
        fullscreen.addActionListener(e -> {
			//JOptionPane.showMessageDialog(jframe, "Resolução | Tela Cheia");
        	//jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        	jframe.setSize(screenSize.width, screenSize.height);
		});

        menu.add(res800x600);
        menu.add(res1024x768);
        menu.addSeparator();
        menu.add(fullscreen);

        return menu;
	}

	private JMenu locale() {
		JMenu menu = new JMenu("Idioma");
		menu.setFont(FontTools.getFontArialPlain20());

		JMenuItem item1 = new JMenuItem("Inglês");
		item1.setFont(FontTools.getFontArialPlain20());
		item1.addActionListener(e -> {
			JOptionPane.showMessageDialog(jframe, "Idioma | InglÊs");
		});
		menu.add(item1);

        JMenuItem item2 = new JMenuItem("Português");
        item2.setFont(FontTools.getFontArialPlain20());
        item2.addActionListener(e -> {
			JOptionPane.showMessageDialog(jframe, "Idioma | Português");
		});
        menu.add(item2);

        return menu;
	}

	private JMenu setupMenuAbout() {
		JMenu menuAbout = new JMenu("Sobre");
		menuAbout.setFont(FontTools.getFontArialPlain20());
		menuAbout.add(details());
		return menuAbout;
	}

	private JMenuItem details() {
		JMenuItem item = new JMenuItem("Detalhes");
		item.setFont(FontTools.getFontArialPlain20());
		item.addActionListener(e -> {
			JOptionPane.showMessageDialog(jframe, "Sobre | Detalhes");
		});
		return item;
	}
}
