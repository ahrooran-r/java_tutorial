package _10_concurrency._6_concurrent_problems._1_dining_philosophers_problem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = null;

        final int numOfPhilosophers = Constants.NUMBER_OF_PHILOSOPHERS.getValue();
        final int numOfChopsticks = Constants.NUMBER_OF_CHOPSTICKS.getValue();

        Philosopher[] philosophers = null;
        Chopstick[] chopsticks;

        try {
            // initialize arrays
            philosophers = new Philosopher[numOfPhilosophers];
            chopsticks = new Chopstick[numOfChopsticks];

            // fill chopsticks
            for (int i = 0; i < numOfChopsticks; i++) chopsticks[i] = new Chopstick(i);

            executorService = Executors.newFixedThreadPool(numOfPhilosophers);
            for (int i = 0; i < numOfPhilosophers; i++) {
                // fill philosopher
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % numOfPhilosophers]);

                // then initiate it as a thread
                executorService.execute(philosophers[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUNNING_TIME.getValue());

            for (Philosopher philosopher : philosophers) {
                philosopher.setFull();
                // this would make philosopher to set he has eaten to the fullest
                // hence break from while loop
            }

        } finally {
            assert executorService != null;
            executorService.shutdown();
            if (!executorService.isTerminated()) {
                System.out.println("Running executor service for 1000 ms more!");
                Thread.sleep(1000);
            }
            for (Philosopher philosopher : philosophers) {
                System.out.printf("Philosopher %s eating counter: %d\n", philosopher, philosopher.getEatingCounter());
            }
        }
    }
}
