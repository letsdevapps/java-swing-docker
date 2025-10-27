package com.pro.ui.configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.pro.exemplos.ContainerInfo;

public class DockerConfiguration {

	private JFrame jframe;
	
	public DockerConfiguration(JFrame jframe) {
		this.jframe = jframe;
	}

	public void autoSetUp() {
		try {
            ProcessBuilder pb = new ProcessBuilder("docker", "ps", "--format", "{{json .}}");
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            List<ContainerInfo> containers = new ArrayList<>();
            Gson gson = new Gson();

            while ((line = reader.readLine()) != null) {
                // Cada linha é um JSON de um container
                ContainerInfo container = gson.fromJson(line, ContainerInfo.class);
                containers.add(container);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Erro ao executar o comando docker");
            }

            // Agora você tem uma lista de containers
            for (ContainerInfo c : containers) {
                System.out.println("ID: " + c.getID() + " | Nome: " + c.getNames() + " | Status: " + c.getStatus());
            }

            String[] colunas = {"ID", "Imagem", "Status", "Portas"};
            DefaultTableModel model = new DefaultTableModel(colunas, 0);

            for (ContainerInfo c : containers) {
                Object[] row = {c.getID(), c.getImage(), c.getStatus(), c.getPorts()};
                model.addRow(row);
            }

            JTable table = new JTable(model);
            
            JScrollPane scrollPane = new JScrollPane(table);
            
            jframe.add(scrollPane);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
