package com.pro.exemplos;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class DockerLocalStackS3Manager extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnStart;
	private JButton btnStop;
	private JTextArea outputArea;

	public DockerLocalStackS3Manager() {
		setTitle("Gerenciador S3 LocalStack Docker");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Painel principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// Botões
		JPanel buttonPanel = new JPanel();
		btnStart = new JButton("Iniciar LocalStack");
		btnStop = new JButton("Parar LocalStack");
		buttonPanel.add(btnStart);
		buttonPanel.add(btnStop);

		// Área de saída
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(outputArea);

		panel.add(buttonPanel, BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);

		add(panel);

		// Ações dos botões
		btnStart.addActionListener(e -> startLocalStack());
		btnStop.addActionListener(e -> stopLocalStack());
	}

	private void startLocalStack() {
	    new Thread(() -> {
	        appendOutput("Iniciando LocalStack com S3...\n");
	        try {
	            ProcessBuilder pb = new ProcessBuilder(
	                "docker", "run", "--rm", "-d", "-p", "4566:4566", "-e", "SERVICES=s3", "localstack/localstack"
	            );

	            // Redireciona a saída padrão e a saída de erro
	            pb.redirectErrorStream(true);
	            Process process = pb.start();

	            // Lê toda a saída do processo
	            StringBuilder output = new StringBuilder();
	            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    output.append(line).append("\n");
	                }
	            }

	            int exitCode = process.waitFor();

	            // Verifica o resultado
	            if (exitCode == 0) {
	                appendOutput("LocalStack com S3 iniciado com sucesso!\n");
	            } else {
	                appendOutput("Erro ao iniciar o LocalStack com S3. Código de saída: " + exitCode + "\n");
	                appendOutput("Detalhes do erro: " + output.toString() + "\n");
	                return;
	            }
	        } catch (IOException | InterruptedException ex) {
	            appendOutput("Erro: " + ex.getMessage() + "\n");
	        }

	        // Aguarde o LocalStack estar pronto
	        boolean ready = false;
	        int retries = 0;
	        while (!ready && retries < 10) { // tenta até 10 vezes
	            try {
	                Thread.sleep(2000); // espera 2 segundos entre tentativas
	                // Testa conexão ao serviço S3
	                S3Client s3Test = S3Client.builder()
	                        .region(Region.US_EAST_1)
	                        .endpointOverride(URI.create("http://localhost:4566"))
	                        .credentialsProvider(StaticCredentialsProvider.create(
	                                AwsBasicCredentials.create("test", "test")
	                        ))
	                        .build();

	                s3Test.listBuckets(); // tenta listar buckets
	                s3Test.close();
	                ready = true;
	            } catch (Exception e) {
	                retries++;
	            }
	        }

	        if (!ready) {
	            appendOutput("LocalStack não ficou pronto a tempo.\n");
	            return;
	        }

	        // Agora cria o cliente S3 e o bucket
	        try {
	            S3Client s3 = S3Client.builder()
	                    .region(Region.US_EAST_1)
	                    .endpointOverride(URI.create("http://localhost:4566"))
	                    .credentialsProvider(StaticCredentialsProvider.create(
	                        AwsBasicCredentials.create("test", "test")
	                    ))
	                    .forcePathStyle(true) // Habilita Path-Style Access para LocalStack
	                    .build();

	            String bucketName = "meu-bucket-test";

	            // Cria o bucket
	            s3.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
	            System.out.println("Bucket criado: " + bucketName);
	            s3.close();
	        } catch (S3Exception e) {
	            System.err.println(e.awsErrorDetails().errorMessage());
	        }
	    }).start();
	}

	private void stopLocalStack() {
		new Thread(() -> {
			appendOutput("Parando e removendo o LocalStack...\n");
			try {
				ProcessBuilder pb = new ProcessBuilder("docker", "rm", "-f", "localstack");
				Process process = pb.start();
				int exitCode = process.waitFor();
				if (exitCode == 0) {
					appendOutput("LocalStack parado com sucesso!\n");
				} else {
					appendOutput("Erro ao parar o LocalStack.\n");
				}
			} catch (IOException | InterruptedException ex) {
				appendOutput("Erro: " + ex.getMessage() + "\n");
			}
		}).start();
	}

	private void appendOutput(String text) {
		SwingUtilities.invokeLater(() -> outputArea.append(text));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new DockerLocalStackS3Manager().setVisible(true);
		});
	}
}
