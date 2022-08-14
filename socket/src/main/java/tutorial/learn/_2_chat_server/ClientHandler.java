package tutorial.learn._2_chat_server;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler implements Runnable, AutoCloseable {

    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String username;

    // This class must keep track of other client handlers as a whole
    private static final Vector<ClientHandler> handlers = new Vector<>(5);

    @SneakyThrows
    public ClientHandler(Socket socket) {
        this.socket = socket;

        try {
            // Assign variables
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            username = ((Message) in.readObject()).from();

            // Add client to the list
            handlers.add(this);

            // Publish a message to console
            Message newlyJoinedMessage = new Message(
                    "server",
                    String.format("Client %s has joined the chat", username));
            broadcast(newlyJoinedMessage);

        } catch (IOException ioException) {
            close();
        }
    }

    @SneakyThrows
    @Override
    public void run() {

        // runs again and again while there is connection
        try {
            while (socket.isConnected()) {

                // Receive the message from client
                Message receivedMessage = (Message) in.readObject();

                // Transfer it to all clients (including this handler's client)
                broadcast(receivedMessage);

            }
        } catch (IOException ioException) {
            close();
        }
    }

    @Override
    public void close() {
        if (null != socket) {
            try {
                socket.close();
                in.close();
                out.close();
            } catch (IOException ignored) {
            }
        }
    }

    /**
     * broadcast message to all clients
     */
    public void broadcast(Message message) {

        // so iterate through all client handlers and get their output stream
        // then place the message on those output stream placeholders
        handlers.forEach(handler -> {
            try {
                handler.out.writeObject(message);
                handler.out.flush();
            } catch (IOException ignored) {
            }
        });
    }
}
