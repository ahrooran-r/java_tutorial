package tutorial.learn.junit;


import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {

        // Launcher API - https://junit.org/junit5/docs/current/user-guide/#launcher-
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(

                        // can select entire package
                        DiscoverySelectors.selectPackage("tutorial.learn.junit")

                        // can select individual class
                        // DiscoverySelectors.selectClass(AnnotationTest.class)
                )
                // .filters(
                //         ClassNameFilter.includeClassNamePatterns(".*Tests")
                // )
                .build();

        final Launcher launcher = LauncherFactory.create();
        final SummaryGeneratingListener listener = new SummaryGeneratingListener();

        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        List<TestExecutionSummary.Failure> failures = listener.getSummary().getFailures();

        failures.forEach(failure -> {
            System.out.println(failure.getException().getLocalizedMessage());
        });

        long successCount = listener.getSummary().getTestsSucceededCount();
        System.out.println("Succeeded tests: " + successCount);
    }
}
