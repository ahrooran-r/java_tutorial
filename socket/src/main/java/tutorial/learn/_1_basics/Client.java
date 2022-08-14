package tutorial.learn._1_basics;

import lombok.SneakyThrows;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private final DataOutputStream out;

    @SneakyThrows
    public Client(InetSocketAddress address) {

        // Socket object immediately attempts to connect
        // No need to call any specific method
        Socket socket = new Socket(address.getAddress(), address.getPort());
        System.out.println("Connected");

        // assign stream
        out = new DataOutputStream(socket.getOutputStream());
    }

    public Client(String host, int port) {
        this(new InetSocketAddress(host, port));
    }

    public Client(InetAddress host, int port) {
        this(new InetSocketAddress(host, port));
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 2300);
        client.sendMessage("Hello Server");

    }

    @SneakyThrows
    public void sendMessage(String message) {
        out.writeUTF(message);
        System.out.println("Message sent");
    }
}
