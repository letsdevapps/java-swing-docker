package com.pro.ui.configuration;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class WindowConfiguration {

	private JFrame jframe;

	public WindowConfiguration() {

	}

	public WindowConfiguration(JFrame jframe) {
		this.jframe = jframe;
	}

	public void setExtendedState() {
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void setFullBorderless() {
		jframe.setUndecorated(true); // Remove a barra de título e bordas
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jframe.setBounds(0, 0, screenSize.width, screenSize.height);
	}
}
