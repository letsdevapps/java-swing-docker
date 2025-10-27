package com.pro;

import javax.swing.SwingUtilities;

import com.pro.frame.MainContentFrame;

public class Main {
	public static void main(String[] args) {
//		JOptionPane.showMessageDialog(null, "Hello");

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainContentFrame().setVisible(true);
			}
		});
	}
}
