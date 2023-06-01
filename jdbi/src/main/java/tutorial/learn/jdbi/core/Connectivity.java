package tutorial.learn.jdbi.core;

import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;

@Slf4j
public class Connectivity {
    public static void main(String[] args) {

        Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306", "root", "password");
        log.info(jdbi.toString());
    }
}
