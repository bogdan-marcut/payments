package com.bogdan.flyway;

import java.util.Optional;

import org.flywaydb.core.Flyway;

import com.google.inject.AbstractModule;

/**
 * @author bogdan.marcut 13/02/2021.
 */
public class FlywayModule extends AbstractModule {

    @Override
    protected void configure() {
	final String url = getEnv("RATPACK_DB_URL", "jdbc:mysql://127.0.0.1:3306/payments");
	final String user = getEnv("RATPACK_DB_USER", "root");
	final String pass = getEnv("RATPACK_DB_PASS", "pass");

	Flyway.configure()
		.dataSource(url, user, pass)
		.load()
		.migrate();
    }

    private static String getEnv(final String key, final String defaultValue) {
	return Optional.ofNullable(System.getenv(key)).orElse(defaultValue);
    }
}
