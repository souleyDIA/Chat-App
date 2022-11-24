
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientChatTCP {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(args[0], Integer.parseInt(args[1])); // connexion au serveur
            System.out.println("Connexion établie avec le serveur " + args[0] + " sur le port " + args[1]); // affichage de la connexion
            try (Scanner sc = new Scanner(System.in)) { // création d'un scanner pour lire les messages
                String message = ""; // initialisation du message
                while (!message.equals("fin")) { // tant que le message n'est pas "fin"
                    System.out.println("Entrez un message : "); // affichage du message
                    message = sc.nextLine(); // lecture du message
                    PrintWriter out = new PrintWriter(socket.getOutputStream()); // création d'un PrintWriter pour envoyer le message
                    out.println(message); // envoi du message
                    out.flush(); // vidage du buffer
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // création d'un BufferedReader pour lire la réponse
                    String messageRecu = in.readLine(); // lecture de la réponse
                    System.out.println("Message reçu : " + messageRecu); // affichage de la réponse
                }
            }
            socket.close(); // fermeture de la connexion
        } catch (IOException e) { // gestion des erreurs
            System.out.println("Aucun serveur TCP n'est joignable");
        }
    }
}

// Lancé avec : javac ClientChatTCP.java && java ClientChatTCP 127.0.0.1 1234
