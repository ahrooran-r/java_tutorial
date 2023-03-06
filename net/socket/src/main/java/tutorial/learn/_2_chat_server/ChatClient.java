package tutorial.learn._2_chat_server;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private final Socket socket;
    private final String username;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    @SneakyThrows
    public ChatClient(Socket socket, String username) {

        // Assign variables
        this.socket = socket;
        this.username = username;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());

        // send first message to server aka its client handler
        Message message = new Message(username, username);
        out.writeObject(message);

        // Listen for messages
        Thread listener = new Thread(() -> {

            while (socket.isConnected()) {
                try {
                    Message received = (Message) in.readObject();
                    String receivedMessage = String.format("%s: %s", received.from(), received.payload());

                    System.out.println(receivedMessage);
                } catch (IOException ignored) {

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        listener.start();
    }

    @SneakyThrows
    public void sendMessage() {
        Scanner scanner = new Scanner(System.in);

        while (socket.isConnected()) {
            String payload = scanner.nextLine();
            Message message = new Message(username, payload);
            out.writeObject(message);
            out.flush();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {

        ChatClient client_1 = new ChatClient(
                new Socket("localhost", 1234),
                "Barbossa");

        ChatClient client_2 = new ChatClient(
                new Socket("localhost", 1234),
                "Sparrow");

        client_1.sendMessage();
    }
}
