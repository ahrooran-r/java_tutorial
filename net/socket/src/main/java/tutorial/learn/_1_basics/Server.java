package tutorial.learn._1_basics;

import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final ServerSocket server;
    private final DataInputStream in;

    @SneakyThrows
    public Server(int port) {

        // create a server
        server = new ServerSocket(port, 1, InetAddress.getByName("localhost"));
        System.out.println("Server started");

        System.out.println("Waiting for client");

        // Wait for a connection and then connect to it
        Socket socket = server.accept();
        System.out.println("Client accepted");

        // assign stream
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public static void main(String[] args) {
        Server server = new Server(2300);
        server.receiveMessage();
    }

    @SneakyThrows
    public void receiveMessage() {
        String message = in.readUTF();
        System.out.println(message);
    }

    public ServerSocket getServer() {
        return server;
    }
}
