package tutorial.learn.collections.agrona;

import org.agrona.concurrent.AgentRunner;

public class _4_Main {

    public static void main(String[] args) {

        _2_CustomAgent customAgent = new _2_CustomAgent();
        _3_CustomIdleStrategy idleStrategy = new _3_CustomIdleStrategy();

        try (AgentRunner agentRunner = new AgentRunner(idleStrategy, null, null, customAgent)) {
            AgentRunner.startOnThread(agentRunner);

            // how to close an agent -> the useful part in using agents is this
            // closeFailAction -> what to do when closing is failed
            agentRunner.close(AgentRunner.RETRY_CLOSE_TIMEOUT_MS, null);
        }

    }
}
