package tutorial.learn.jdbi.core;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Connectivity {
    public static void main(String[] args) throws InterruptedException {

        // Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306", "root", "password");
        // log.info(jdbi.toString());

        Thread t = Thread.ofVirtual().start(() -> System.out.println("Hello World"));
        t.join();
    }
}
