package tutorial.learn.code_snippets.snippet_1_logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import tutorial.learn.code_snippets.snippet_1_logging.log_config.LogManager;

import java.nio.file.Paths;

@Slf4j
public class Main {

    static {

        // https://stackoverflow.com/a/31117271/10582056

        // assume SLF4J is bound to logback in the current environment
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        // determine environmental specific configuration path
        String path = Paths.get("code_snippets", "config", "logback.xml").toAbsolutePath().toString();

        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset();
            configurator.doConfigure(path);
        } catch (JoranException ignored) {
            // StatusPrinter will handle this
        }
    }

    @SneakyThrows
    public static void main(String[] args) {

        // all should print
        LogManager.APP.trace("APP_TRACE");
        LogManager.APP.debug("APP_DEBUG");
        LogManager.APP.info("APP_INFO");
        LogManager.APP.warn("APP_WARN");
        LogManager.APP.error("APP_ERROR");

        LogManager.IN.trace("IN_TRACE");
        // following should print
        LogManager.IN.debug("IN_DEBUG");
        LogManager.IN.info("IN_INFO");
        LogManager.IN.warn("IN_WARN");
        LogManager.IN.error("IN_ERROR");

        LogManager.OUT.trace("OUT_TRACE");
        LogManager.OUT.debug("OUT_DEBUG");
        // following should print
        LogManager.OUT.info("OUT_INFO");
        LogManager.OUT.warn("OUT_WARN");
        LogManager.OUT.error("OUT_ERROR");

        LogManager.DB.trace("DB_TRACE");
        LogManager.DB.debug("DB_DEBUG");
        LogManager.DB.info("DB_INFO");
        // following should print
        LogManager.DB.warn("DB_WARN");
        LogManager.DB.error("DB_ERROR");

        LogManager.KAFKA.trace("KAFKA_TRACE");
        LogManager.KAFKA.debug("KAFKA_DEBUG");
        LogManager.KAFKA.info("KAFKA_INFO");
        LogManager.KAFKA.warn("KAFKA_WARN");
        // only error should print
        LogManager.KAFKA.error("KAFKA_ERROR");

        // nothing should print
        LogManager.SUMMARY.trace("SUMMARY_TRACE");
        LogManager.SUMMARY.debug("SUMMARY_DEBUG");
        LogManager.SUMMARY.info("SUMMARY_INFO");
        LogManager.SUMMARY.warn("SUMMARY_WARN");
        LogManager.SUMMARY.error("SUMMARY_ERROR");

        // all should print -> this is not related to our own framework
        log.trace("OTHER_TRACE");
        log.debug("OTHER_DEBUG");
        log.info("OTHER_INFO");
        log.warn("OTHER_WARN");
        log.error("OTHER_ERROR");

        // Sleep is needed to stall the main process from terminating
        // so logger can create the files and write the data
        Thread.sleep(3_000);
    }
}
