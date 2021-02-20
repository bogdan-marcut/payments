package com.bogdan.status;

import com.bogdan.status.repository.MonitoringRepository;
import com.bogdan.status.repository.MonitoringRepositoryImpl;
import com.bogdan.status.service.MonitoringChain;
import com.google.inject.AbstractModule;

/**
 * @author bogdan.marcut 20/02/2021.
 */
public class MonitoringModule extends AbstractModule {

    @Override
    protected void configure() {
	this.bind(MonitoringChain.class);
	this.bind(MonitoringRepository.class).to(MonitoringRepositoryImpl.class);
    }

}
