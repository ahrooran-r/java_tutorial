package tutorial.learn.code_snippets.snippet_1_logging;

import static tutorial.learn.code_snippets.snippet_1_logging.log_config.LogManager.*;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

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
        APP.trace("APP_TRACE");
        APP.debug("APP_DEBUG");
        APP.info("APP_INFO");
        APP.warn("APP_WARN");
        APP.error("APP_ERROR");

        IN.trace("IN_TRACE");
        // following should print
        IN.debug("IN_DEBUG");
        IN.info("IN_INFO");
        IN.warn("IN_WARN");
        IN.error("IN_ERROR");

        OUT.trace("OUT_TRACE");
        OUT.debug("OUT_DEBUG");
        // following should print
        OUT.info("OUT_INFO");
        OUT.warn("OUT_WARN");
        OUT.error("OUT_ERROR");

        DB.trace("DB_TRACE");
        DB.debug("DB_DEBUG");
        DB.info("DB_INFO");
        // following should print
        DB.warn("DB_WARN");
        DB.error("DB_ERROR");

        KAFKA.trace("KAFKA_TRACE");
        KAFKA.debug("KAFKA_DEBUG");
        KAFKA.info("KAFKA_INFO");
        KAFKA.warn("KAFKA_WARN");
        // only error should print
        KAFKA.error("KAFKA_ERROR");

        // nothing should print
        SUMMARY.trace("SUMMARY_TRACE");
        SUMMARY.debug("SUMMARY_DEBUG");
        SUMMARY.info("SUMMARY_INFO");
        SUMMARY.warn("SUMMARY_WARN");
        SUMMARY.error("SUMMARY_ERROR");

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
