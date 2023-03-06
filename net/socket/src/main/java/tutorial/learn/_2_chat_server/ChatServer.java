package tutorial.learn._2_chat_server;

import lombok.SneakyThrows;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @param server Servers connect via ServerSocket
 */
public record ChatServer(ServerSocket server) implements AutoCloseable {

    private static final ExecutorService handlers = Executors.newCachedThreadPool();

    @SneakyThrows
    public void start() {

        // Check whether server is closed
        while (!server.isClosed()) {
            final Socket socket = server.accept();

            // socket#getRemoteSocketAddress() is the address of device connected to the other end of socket
            // the other device can either be a server or in this case, a client
            System.out.printf("New client connected: %s", socket.getRemoteSocketAddress());

            // Now we need to create a separate thread to handle the client
            // Because of JAVA's blocking nature, a thread needs to be specifically assigned to every client

            ClientHandler handler = new ClientHandler(socket);
            handlers.submit(handler);
        }
    }

    @Override
    @SneakyThrows
    public void close() {
        if (null != server) {
            server.close();
            handlers.shutdownNow();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        ServerSocket server = new ServerSocket(1234, 5, InetAddress.getByName("localhost"));
        ChatServer chatServer = new ChatServer(server);
        chatServer.start();
    }
}
