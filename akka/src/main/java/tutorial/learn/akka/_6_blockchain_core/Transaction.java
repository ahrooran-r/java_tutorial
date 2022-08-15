package tutorial.learn.akka._6_blockchain_core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class Transaction implements Serializable {

    private int id;

    private long timestamp;

    private int accountNumber;

    private double amount;

}
