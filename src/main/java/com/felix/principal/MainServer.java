package com.felix.principal;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.felix.conversor.Converter;

public class MainServer {

	public static void main(String[] args) throws IOException {
		while(true) {
		int Port = 12345;
		
//		Cria um serviÃ§o Socket
		ServerSocket server = new ServerSocket(Port);
		System.out.println("Socket Criado :: Port = " + Port);

//		Cria um canal de comunicao
		System.out.println("Esperando Conexão...");
		Socket cliente = server.accept(); // blocante
		System.out.println("Conectado ao cliente");
		

//			Recebe e envia informações ao cliente
			Scanner InServer = new Scanner(cliente.getInputStream());
			PrintStream OutServer = new PrintStream(cliente.getOutputStream());

			OutServer.println("Conectado");
			OutServer.println("Servidor requisitando dados...");

			System.out.println("Recebendo Dados do cliente");
			String recebidoCsv = InServer.nextLine();
			String recebidoJson = InServer.nextLine();
			System.out.println("Dados Recebidos\nCsv    :: " + recebidoCsv + "\nFolder :: " + recebidoJson);

			System.out.println("Começando conversão");
			File fileCSV = new File(recebidoCsv);
			File fileJson = new File(recebidoJson);

			try {
				Converter converter = new Converter();
				converter.convert(fileCSV, fileJson);
				OutServer.println("Sucesso");
				System.out.println("Arquivo convertido com sucesso");
			} catch (Exception e) {
				System.out.println("ERRO :: Arquivo não foi convertido");
			}
		}
	}

}
