package tutorial.learn.akka._5_racing_game_akka;

import akka.actor.typed.ActorSystem;

public class Main {
    public static void main(String[] args) {

        ActorSystem<CommonCommand> monitor = ActorSystem.create(MonitorBehavior.create(), "monitor");
        monitor.tell(new MonitorBehavior.InstructionCommand("start"));
    }
}
