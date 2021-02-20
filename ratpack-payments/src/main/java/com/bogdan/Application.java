package com.bogdan;

import java.util.Optional;

import ratpack.func.Action;
import ratpack.guice.BindingsSpec;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.handling.RequestLogger;
import ratpack.hikari.HikariModule;
import ratpack.server.RatpackServer;
import ratpack.server.RatpackServerSpec;
import ratpack.server.ServerConfig;
import ratpack.server.ServerConfigBuilder;

import com.bogdan.employee.EmployeeModule;
import com.bogdan.employee.service.EmployeeChain;
import com.bogdan.flyway.FlywayModule;
import com.bogdan.metrics.MetricsModule;
import com.bogdan.metrics.service.MetricsChain;
import com.bogdan.status.MonitoringModule;
import com.bogdan.status.service.MonitoringChain;
import com.zaxxer.hikari.HikariConfig;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public class Application {

    private static final int PORT = 5050;

    public static void main(final String[] args) throws Exception {
	RatpackServer.start(initServer());
    }

    private static Action<RatpackServerSpec> initServer() {
	return server -> server
		.serverConfig(generateServerConfig())
		.registryOf(registry -> registry.add(JsonMapping.getJsonMapping()))
		.registry(Guice.registry(buildDependencies()))
		.handlers(buildChain());
    }

    private static ServerConfigBuilder generateServerConfig() {
	return ServerConfig.embedded()
		.port(PORT);
    }

    private static Action<BindingsSpec> buildDependencies() {
	return bindings -> bindings
		.module(FlywayModule.class)
		.module(HikariModule.class, generateDatabaseConfig())
		.module(MetricsModule.class)
		.module(MonitoringModule.class)
		.module(EmployeeModule.class)
		;
    }

    private static Action<HikariConfig> generateDatabaseConfig() {
	return hikariConfig -> {
	    hikariConfig.setDriverClassName(getEnv("RATPACK_DB_DRIVER", "com.mysql.cj.jdbc.Driver"));
	    hikariConfig.setJdbcUrl(getEnv("RATPACK_DB_URL", "jdbc:mysql://localhost:3306/payments"));
	    hikariConfig.setUsername(getEnv("RATPACK_DB_USER", "root"));
	    hikariConfig.setPassword(getEnv("RATPACK_DB_PASS", "pass"));
	};
    }

    private static String getEnv(final String key, final String defaultValue) {
	return Optional.ofNullable(System.getenv(key))
		.orElse(defaultValue);
    }

    private static Action<Chain> buildChain() {
	return chain -> chain
		.all(RequestLogger.ncsa())
		.prefix("api", buildApi());
    }

    private static Action<Chain> buildApi() {
	return chain -> chain
		.insert(MetricsChain.class)
		.insert(MonitoringChain.class)
		.insert(EmployeeChain.class)
		;
    }

}
