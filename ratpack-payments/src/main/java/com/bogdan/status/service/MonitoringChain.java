package com.bogdan.status.service;

import java.lang.management.ManagementFactory;
import java.util.Date;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.handling.Handler;
import ratpack.http.MediaType;
import ratpack.http.internal.HttpHeaderConstants;

import com.bogdan.JsonMapping;
import com.bogdan.status.model.MonitoringServiceStateDto;
import com.bogdan.status.model.MonitoringStatusDto;
import com.bogdan.status.repository.MonitoringRepository;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

/**
 * @author bogdan.marcut 20/02/2021.
 */
public class MonitoringChain implements Action<Chain> {

    private final MonitoringRepository monitoringRepository;

    @Inject
    public MonitoringChain(final MonitoringRepository monitoringRepository) {
	this.monitoringRepository = monitoringRepository;
    }

    @Override
    public void execute(final Chain chain) throws Exception {
	chain.prefix("monitoring", this.defineMonitoringApi());
    }

    private Action<Chain> defineMonitoringApi() {
	return chain -> chain
		.get("status", this.getStatus());
    }

    private Handler getStatus() {
	return ctx -> ctx
		.header(HttpHeaderConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON)
		.getResponse()
		.send(JsonMapping.toJson(this.buildStatus()));
    }

    private MonitoringStatusDto buildStatus() {
	final MonitoringServiceStateDto databaseStatus = this.monitoringRepository.getDatabaseStatus();
	final long startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
	return MonitoringStatusDto.builder()
		.available(databaseStatus.getAvailable())
		.environment("dev")
		.version("")
		.uptime(new Date(startTime).toString())
		.projectName("")
		.externalServices(ImmutableList.of(databaseStatus))
		.build();
    }

}
