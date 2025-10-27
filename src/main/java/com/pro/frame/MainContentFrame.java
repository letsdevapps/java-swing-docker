package com.pro.frame;

import javax.swing.JFrame;

import com.pro.ui.configuration.DockerConfiguration;
import com.pro.ui.configuration.LocaleConfiguration;
import com.pro.ui.configuration.MenuConfiguration;
import com.pro.ui.configuration.PanelConfiguration;
import com.pro.ui.configuration.WindowConfiguration;

public class MainContentFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainContentFrame() {
		setTitle("Docker Manager");
		setSize(1024, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centraliza a janela
		// pack(); // Ajusta o tamanho ao conteúdo

		LocaleConfiguration localeConfig = new LocaleConfiguration();
		localeConfig.autoSetUp();

		MenuConfiguration menuConfig = new MenuConfiguration(this);
		menuConfig.autoSetUp();

		PanelConfiguration panelConfig = new PanelConfiguration(this);
		//panelConfig.autoSetUp();

		WindowConfiguration windowConfig = new WindowConfiguration(this);
		//windowConfig.setExtendedState();
		//windowConfig.setFullBorderless();

		DockerConfiguration dockerConfig = new DockerConfiguration(this);
		//dockerConfig.autoSetUp();

		//BackgroundImageConfiguration2 bgiConfig = new BackgroundImageConfiguration2(this);
		//bgiConfig.autoSetUp();
	}
}
