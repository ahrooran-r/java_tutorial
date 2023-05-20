package tutorial.learn.code_snippets.snippet_1_logging.log_config;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * So, since we are using @Deligate on the logger to reduce the boilerplate code,
 * the class is not correctly printed.
 * The stack trace would have the LogManager class as most recent caller.
 * So, we alter this converter to select the next in line class
 * (which is the actual class that logger called).
 */
public class DelegateAwareClassCallerConverter extends ClassOfCallerConverter {
    @Override
    protected String getFullyQualifiedName(ILoggingEvent event) {

        StackTraceElement[] cda = event.getCallerData();
        if (cda != null && cda.length > 1) {
            return cda[1].getClassName();
        } else {
            return super.getFullyQualifiedName(event);
        }
    }
}
