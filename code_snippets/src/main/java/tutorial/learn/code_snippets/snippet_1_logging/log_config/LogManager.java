package tutorial.learn.code_snippets.snippet_1_logging.log_config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.google.common.base.Throwables;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public enum LogManager {

    APP("app"),
    IN("in"),
    OUT("out"),
    DB("db"),
    KAFKA("kafka"),
    SUMMARY("summary");

    private final Path path = Paths.get("code_snippets", "config", "log-settings.yml");

    @Delegate
    @Getter(AccessLevel.NONE)
    private Logger logger;

    private String file;

    private Level level;

    private boolean enabled;

    private boolean enableConsole;

    LogManager(String name) {

        try {

            ObjectMapper mapper = YAMLMapper
                    .builder()
                    .addModule(new AfterburnerModule())
                    .build();

            JsonNode logConfig = mapper
                    .readTree(Files.newBufferedReader(path, StandardCharsets.UTF_8))
                    .get(name);

            this.file = logConfig.get("file").asText();
            this.enabled = logConfig.get("enabled").asBoolean();
            this.enableConsole = logConfig.get("console").asBoolean();
            this.level = mapper.treeToValue(logConfig.get("level"), Level.class);

            // Useful when searching for loggers because user given names are like "in", "db" etc.
            // which can be too short to discriminate from other names. For example a class with package name "spring" has "in".
            // So just by discriminating with "in" would give wrong results.

            // I'm adding package name + user given name as logger name because it is needed in logback.xml
            // look following line in that xml file -> <logger name="tutorial.learn.code_snippets" level="DEBUG">
            this.logger = LoggerFactory.getLogger("tutorial.learn.code_snippets" + "." + name);

        } catch (IOException ioException) {
            System.out.println(Throwables.getStackTraceAsString(ioException));

            // https://github.com/arzzen/all-exit-error-codes/blob/master/programming-languages/java/errors.md
            System.exit(1039);
        }
    }

    public static LogManager get(String fullyQualifiedName) {
        final String prefix = "tutorial.learn.code_snippets.";
        return switch (fullyQualifiedName.toLowerCase()) {
            case prefix + "app" -> LogManager.APP;
            case prefix + "in" -> LogManager.IN;
            case prefix + "out" -> LogManager.OUT;
            case prefix + "db" -> LogManager.DB;
            case prefix + "kafka" -> LogManager.KAFKA;
            case prefix + "summary" -> LogManager.SUMMARY;
            default -> null;
        };
    }
}
