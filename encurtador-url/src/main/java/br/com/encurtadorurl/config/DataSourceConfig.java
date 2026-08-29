package br.com.encurtadorurl.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@DataSourceDefinition(
        name = "java:app/jdbc/EncurtadorURLDS",
        className = "org.h2.jdbcx.JdbcDataSource",
        url = "jdbc:h2:mem:urlshortener;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
        user = "sa",
        password = "sa"
)
@Singleton
@Startup
public class DataSourceConfig {
}
