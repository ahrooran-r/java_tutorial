package tutorial.learn.jdbi.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;

import javax.sql.DataSource;

@Slf4j
public class Persistence {

    private HikariConfig hikariConfig() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName(String.format("HikariPool--[%s.class]", this.getClass().getSimpleName()));
        hikariConfig.setJdbcUrl("jdbc:postgresql://pricedb:5444/dfnmkt");
        hikariConfig.setUsername("mubasher");
        hikariConfig.setPassword("password");
        hikariConfig.setConnectionTimeout(60_000L);
        hikariConfig.setMaximumPoolSize(2);
        return hikariConfig;
    }

    private DataSource datasource(HikariConfig config) {
        return new HikariDataSource(config);
    }

    private Jdbi postgres(DataSource dataSource) {
        return Jdbi
                .create(dataSource)
                .installPlugin(new PostgresPlugin());
    }
}
