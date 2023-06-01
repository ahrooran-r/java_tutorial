package tutorial.learn.code_snippets.snippet_1_logging.log_config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;

public class ConditionalConsoleAppender extends ConsoleAppender<ILoggingEvent> {
    @Override
    protected void subAppend(ILoggingEvent event) {
        LogManager logger = LogManager.get(event.getLoggerName());
        if (null != logger && logger.isEnableConsole()) super.subAppend(event);
    }
}
