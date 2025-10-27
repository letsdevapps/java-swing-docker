package com.pro.ui.configuration;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;

public class LocaleConfiguration {

	private JFrame jframe;

	public LocaleConfiguration() {
	}

	public LocaleConfiguration(JFrame jframe) {
		this.jframe = jframe;
	}

	public void autoSetUp() {
		setPtBr();
	}

	public void setEnUs() {
		Locale locale = new Locale("en", "US");
		ResourceBundle bundle = ResourceBundle.getBundle("messages/messages", locale);
		System.out.println(bundle.getString("helloWorld"));
	}

	public void setPtBr() {
		Locale locale = new Locale("pt", "BR");
		ResourceBundle bundle = ResourceBundle.getBundle("messages/messages_pt-br", locale);
		System.out.println(bundle.getString("helloWorld"));
	}
}
