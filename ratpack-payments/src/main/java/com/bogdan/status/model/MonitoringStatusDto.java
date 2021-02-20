package com.bogdan.status.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author bogdan.marcut 20/02/2021.
 */
@Getter
@AllArgsConstructor
@Builder
@ToString
public class MonitoringStatusDto {

    private final Boolean available;

    private final String environment;

    private final String version;

    private final String uptime;

    private final String projectName;

    private final List<MonitoringServiceStateDto> externalServices;

}
