package tutorial.learn.collections.agrona;

import org.agrona.concurrent.Agent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * An Agent can be scheduled on a dedicated thread, or it can be run as
 * part of a composite group of agents on a single thread.
 */
public class _2_CustomAgent implements Agent {

    private AtomicInteger counter;

    /**
     * When an agent starts it calls this method.
     * We can use it to init resources, like a @PostConstruct in spring boot
     */
    @Override
    public void onStart() {
        System.out.println("Init counter");
        counter = new AtomicInteger(5);
    }

    /**
     * A typical duty cycle will poll the doWork function of an agent until it returns zero.
     * Once the zero is returned, the idle strategy will be called.
     */
    @Override
    public int doWork() throws Exception {
        int x = counter.getAndDecrement();
        System.out.println("Process complex shit! -> " + x);
        return x;
    }

    @Override
    public void onClose() {
        counter = null;
    }

    @Override
    public String roleName() {
        return "Ahrooran's Agent :()";
    }

    /*
     * Implementations:
     *
     * 1. CompositeAgent -> Group several Agents into one composite, so they can be scheduled as a unit.
     * CompositeAgent agent = new CompositeAgent(new _2_CustomAgent(), new _2_CustomAgent())
     *
     * 2. DynamicCompositeAgent -> Group several Agents into one composite, so they can be scheduled as a unit.
     *                              Agents can be dynamically added and removed.
     */
}
