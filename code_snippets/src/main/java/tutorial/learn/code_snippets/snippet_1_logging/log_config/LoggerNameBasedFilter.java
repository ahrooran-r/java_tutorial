package tutorial.learn.code_snippets.snippet_1_logging.log_config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * <a href="https://developpaper.com/use-logback-filter-to-filter-a-class-and-mask-a-class/">based on this</a>
 * <p>
 * Each logger has its own threshold level {@link LogManager#getLevel()}}.
 * This filter looks at each log event, check whether it comes from the specified loggers, and if so would enforce the
 * threshold level.
 */
public class LoggerNameBasedFilter extends Filter<ILoggingEvent> {

    /**
     * Suppose an event is emitted from {@link LogManager#APP}, then this method would compare
     * threshold level of event with that of SERVER (which is already specified by developer)
     *
     * @param event a log event like SERVER.info(...)
     * @return whether to accept said event or not
     */
    @Override
    public FilterReply decide(ILoggingEvent event) {

        // this is for events which are originated from our LogManager
        LogManager logger = LogManager.get(event.getLoggerName());
        if (null != logger) {

            boolean isLevelSatisfied = event.getLevel().isGreaterOrEqual(Level.toLevel(logger.getLevel().name()));

            if (logger.isEnabled()) return isLevelSatisfied ? FilterReply.ACCEPT : FilterReply.DENY;
            else return FilterReply.DENY;

        } else {
            return FilterReply.ACCEPT;
        }
    }
}
