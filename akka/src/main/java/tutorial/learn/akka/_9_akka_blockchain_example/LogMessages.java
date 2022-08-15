package tutorial.learn.akka._9_akka_blockchain_example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LogMessages {
    FAILED_TO_FIND_HASH("Hash calculation is failed");

    private final String correspondingString;
}
