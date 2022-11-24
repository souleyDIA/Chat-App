import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChatTCP {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0])); // creation socket serveur
            System.out.println("Serveur à l'écoute du port " + args[0]); // affichage du port d'écoute
            Socket socket = serverSocket.accept(); // attente connexion client
            System.out.println("Un client s'est connecté"); // affichage connexion client
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) { // création buffer d'entrée
                String messageRecu = ""; // initialisation message reçu
                while (!messageRecu.equals("fin")) { // tant que le message reçu n'est pas "fin"
                    messageRecu = in.readLine(); // lecture message reçu
                    System.out.println("Message reçu : " + messageRecu); // affichage message reçu
                    System.out.println("Entrez un message : "); // affichage message à envoyer
                    String message = new BufferedReader(new InputStreamReader(System.in)).readLine(); // lecture message à envoyer
                    PrintWriter out = new PrintWriter(socket.getOutputStream()); // création buffer de sortie
                    out.println(message); // envoi message
                    out.flush(); // vidage buffer
                }
            }
            socket.close(); // fermeture socket
            serverSocket.close(); // fermeture socket serveur
        } catch (IOException e) { // gestion erreur
            System.out.println("Un serveur est déjà à l'écoute sur le port " + args[0]); 
        }
    }
}

// Lancé avec : javac ServerChatTCP.java && java ServerChatTCP 1234
