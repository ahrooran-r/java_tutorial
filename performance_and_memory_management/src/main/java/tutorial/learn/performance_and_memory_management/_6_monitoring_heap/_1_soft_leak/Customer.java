package tutorial.learn.performance_and_memory_management._6_monitoring_heap._1_soft_leak;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record Customer(
        int id,
        String name
) {
    public Customer(String name) {
        this(0, name);
    }

    @Override
    public String toString() {
        return id + " : " + name;
    }
}

