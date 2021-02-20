package com.bogdan.status.model;

import java.util.Map;

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
public class MonitoringServiceStateDto {

    private final Boolean available;

    private final String serviceName;

    private final String errorMessage;

    private final Map<String, String> data;

}
