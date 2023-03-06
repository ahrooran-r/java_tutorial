package tutorial.learn._2_chat_server;

import java.io.Serializable;

record Message(String from, String payload) implements Serializable {
}
