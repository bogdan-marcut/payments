package com.bogdan.status.repository;

import com.bogdan.status.model.MonitoringServiceStateDto;

/**
 * @author bogdan.marcut 20/02/2021.
 */
public interface MonitoringRepository {

    MonitoringServiceStateDto getDatabaseStatus();

}
