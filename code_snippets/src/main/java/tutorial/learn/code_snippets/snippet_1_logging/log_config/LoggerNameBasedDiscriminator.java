package tutorial.learn.code_snippets.snippet_1_logging.log_config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.sift.Discriminator;

/**
 * <a href="https://stackoverflow.com/a/18398028/10582056">based on this</a>
 * <p>
 * For logging we have separate loggers like {@link LogManager#IN}, {@link LogManager#OUT} etc.
 * Each of these loggers should be associated with a separate file specified in {@link LogManager#getFile()}.
 * This discriminator allows that operation by analyzing each log event and pushing them to separate log files.
 * This discriminator is mentioned in `SIFT` appender in `logback.xml` which enables this behaviour.
 * All other loggers are logged in "-app.log"
 */
public class LoggerNameBasedDiscriminator implements Discriminator<ILoggingEvent> {

    private static final String KEY = "fileName";

    private boolean started;

    /**
     * Whenever a logging event is given, it searches through LogManager for `name` of event.
     *
     * @return associated file name for said logger. if the logging event comes from {@link LogManager#IN} logger,
     * the returned string would say "in" which makes the log to be written to "in.log" file.
     */
    @Override
    public String getDiscriminatingValue(ILoggingEvent event) {
        // the discriminating value is here is the file name
        String fileName = LogManager.get(event.getLoggerName()).getFile();
        return null != fileName ? fileName : "app";
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public void start() {
        started = true;
    }

    @Override
    public void stop() {
        started = false;
    }

    @Override
    public boolean isStarted() {
        return started;
    }
}
