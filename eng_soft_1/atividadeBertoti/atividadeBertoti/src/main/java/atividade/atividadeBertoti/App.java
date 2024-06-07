package atividade.atividadeBertoti;

import java.util.Scanner;

public class App {
	
    public static void main( String[] args ) {
    	ChatController chat =  new ChatController();
    	Scanner leitura = new Scanner(System.in);
    	String resposta = "";
    	String input = "";
    	
    	while (true) {
    		System.out.println("Comando para a IA:");
    		input = leitura.nextLine();
    		
    		if (!input.equals("parar")) {
    			resposta = chat.resposta(input);
        		System.out.println(String.format("Resposta da IA: \n %s", resposta));
    		} else {
    			System.out.println("Conversa acabaou!");
    			break;
    		}
    		
    	}
    }
    
}
