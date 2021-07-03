package _10_concurrency._5_concurrent_collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class _6_ConcurrentMap {
    public static void main(String[] args) {

        final Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>(5);

        MapWorker worker = new MapWorker(concurrentHashMap);

        new Thread(worker::addToQueue).start();
        new Thread(worker::removeFromQueue).start();
    }
}

class MapWorker {

    private final Map<String, Integer> concurrentHashMap;

    public MapWorker(Map<String, Integer> concurrentHashMap) {
        this.concurrentHashMap = concurrentHashMap;
    }

    public void addToQueue() {
        try {
            concurrentHashMap.put("B", 1);
            concurrentHashMap.put("H", 2);
            Thread.sleep(1000);
            concurrentHashMap.put("F", 3);
            concurrentHashMap.put("A", 4);
            Thread.sleep(1000);
            concurrentHashMap.put("E", 5);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void removeFromQueue() {
        try {
            Thread.sleep(5000);
            System.out.println(concurrentHashMap.get("A"));
            Thread.sleep(1000);
            System.out.println(concurrentHashMap.get("E"));
            Thread.sleep(1000);
            System.out.println(concurrentHashMap.get("C"));
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
