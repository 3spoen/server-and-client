package AUf1;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<Client> userThreads = new HashSet<>();

    public Server(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Chat Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");

                Client newUser = new Client(socket, this);
                userThreads.add(newUser);
                newUser.start();

            }

        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        Server server = new Server(port);
        server.execute();
    }

    /**
     * Delivers a message from one user to others (broadcasting)
     */
    void broadcast(String message, Client excludeUser) {
        for (Client aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }

    /**
     * Stores username of the newly connected client.
     */
    void addUserName(String userName) {
        userNames.add(userName);
    }

    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    void removeUser(String userName, Client aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(userName);
            System.out.println("The user " + userName + " quitted");
        }
    }

    Set<String> getUserNames() {
        return this.userNames;
    }

    /**
     * Returns true if there are other users connected
     */
    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }

    public class ReadThread extends Thread {
        private BufferedReader reader;
        private Socket socket;
        private Client client;

        public ReadThread(Socket socket, Client client) {
            this.socket = socket;
            this.client = client;

            try {
                InputStream input = socket.getInputStream();
                reader = new BufferedReader(new InputStreamReader(input));
            } catch (IOException ex) {
                System.out.println("Error getting input stream: " + ex.getMessage());

            }
        }

        public void run() {
            while (true) {
                try {
                    String response = reader.readLine();
                    System.out.println("\n" + response);

                    // prints the username after displaying the server's message
                    if (client.getUserNames() != null) {
                        System.out.print("[" + client.getUserName() + "]: ");
                    }
                } catch (IOException ex) {
                    System.out.println("Error reading from server: " + ex.getMessage());

                    break;
                }
            }
        }
    }
}
