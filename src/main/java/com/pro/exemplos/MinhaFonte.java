package com.pro.exemplos;

import javax.swing.*;
import java.awt.*;

public class MinhaFonte extends JFrame {

	private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        // Cria a fonte desejada com tamanho maior
        Font fontPadrao = new Font("Arial", Font.PLAIN, 20);

        // Aplica essa fonte para todos os componentes padrão do UIManager
        UIManager.put("Label.font", fontPadrao);
        UIManager.put("Button.font", fontPadrao);
        UIManager.put("TextField.font", fontPadrao);
        UIManager.put("TextArea.font", fontPadrao);
        UIManager.put("ComboBox.font", fontPadrao);
        UIManager.put("CheckBox.font", fontPadrao);
        UIManager.put("RadioButton.font", fontPadrao);
        UIManager.put("TabbedPane.font", fontPadrao);
        UIManager.put("Menu.font", fontPadrao);
        UIManager.put("MenuItem.font", fontPadrao);
        UIManager.put("PopupMenu.font", fontPadrao);
        // Adicione outros componentes se necessário

        // Executa a interface
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Aplicação com Fonte Global");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            panel.add(new JLabel("Este é um label"));
            panel.add(new JButton("Botão"));
            panel.add(new JTextField("Campo de texto", 15));
            panel.add(new JCheckBox("Checkbox"));
            panel.add(new JRadioButton("RadioButton"));

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}