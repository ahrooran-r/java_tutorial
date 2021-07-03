package _10_concurrency._3_multi_threading_concepts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

enum Downloader {
    INSTANCE;

    private final Semaphore semaphore = new Semaphore(3, true);

    public void downloadData() {
        try {
            semaphore.acquire();
            download();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void download() {
        System.out.println("Downloading content...");
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(1000));
    }
}

/**
 * - semaphores maintains a set of permits
 * - acquire() -> if a permit is available, then take it
 * - release() -> releases a permit
 * <p>
 * - semaphore -> just keep the number of count available
 * - new Semaphore(int permits, boolean fair)
 */
public class _5_Semaphores {
    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 12; i++) service.execute(Downloader.INSTANCE::downloadData);
        // you can see 3  threads running at the same time
        // this is because semaphore is restricting access to the resource to only 3 threads at a time
        // so for 12 threads, it will be 4 iterations with 1000 ms gap

        // so in essence
        // locks, synchronization, mutex -> can be used to block access for a thread
        // semaphores -> can be used to block access for a resource

        /*
         * e.g.:
         * consider a situation where food is in a big bowl and 10 members want to take food from it
         * with SEMAPHORE, you can allow only 2 people to take from the bowl at the SAME time!
         * rest of the 8 people have to wait
         * once a guy has taken the food, another guy can come in
         * or if 2 guys taken food, then another 2 guys can come in
         *
         * you cannot control threads as separate groups, allowing one group to execute while other waits...
         * */
    }
}
