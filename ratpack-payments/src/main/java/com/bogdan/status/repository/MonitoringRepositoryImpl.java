package com.bogdan.status.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bogdan.status.model.MonitoringServiceStateDto;
import com.google.common.collect.ImmutableMap;

/**
 * @author bogdan.marcut 20/02/2021.
 */
public class MonitoringRepositoryImpl implements MonitoringRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DSLContext create;

    @Inject
    public MonitoringRepositoryImpl(final DataSource connection) {
	this.create = DSL.using(connection, SQLDialect.MYSQL);
    }

    @Override
    public MonitoringServiceStateDto getDatabaseStatus() {
	try {
	    return MonitoringServiceStateDto.builder()
		    .available(true)
		    .serviceName("mysql")
		    .data(this.buildDatabaseFields())
		    .build();
	} catch (final DataAccessException e) {
	    this.logger.error("[MONITORING] Database error", e);
	    return MonitoringServiceStateDto.builder()
		    .available(false)
		    .serviceName("mysql")
		    .build();
	}
    }

    private Map<String, String> buildDatabaseFields() {
	final String uptime = this.getDatabaseUptimeInDays();
	return ImmutableMap.<String, String>builder()
		.put("location", this.getDatabaseName())
		.put("uptime", uptime)
		.build();
    }

    private String getDatabaseName() {
	final Result<Record> result = this.create.fetch("SELECT DATABASE()");
	return result.stream()
		.findFirst()
		.map(record -> record.get(0))
		.map(String::valueOf)
		.orElse("");
    }

    private String getDatabaseUptimeInDays() {
	final ZonedDateTime now = LocalDateTime.now()
		.atZone(ZoneId.systemDefault());
	final Result<Record> result = this.create.fetch("SHOW GLOBAL STATUS LIKE 'uptime'");
	return result.stream()
		.findFirst()
		.map(record -> record.get(1))
		.map(String::valueOf)
		.map(Long::valueOf)
		.map(s -> now.minus(s, ChronoField.SECOND_OF_MINUTE.getBaseUnit()))
		.map(localDateTime -> localDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME))
		.orElse("");
    }
}
