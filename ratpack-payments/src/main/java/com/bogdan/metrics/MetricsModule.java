package com.bogdan.metrics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bogdan.metrics.service.MetricsChain;
import com.google.inject.AbstractModule;

/**
 * @author bogdan.marcut 20/02/2021.
 */
public class MetricsModule extends AbstractModule {

    private static final String MICROMETER_DISK_METRICS = "./MicrometerDiskMetrics";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void configure() {
	this.bind(MetricsChain.class);
	this.bind(PrometheusMeterRegistry.class).toInstance(this.buildPrometheusInstance());
    }

    private PrometheusMeterRegistry buildPrometheusInstance() {
	final PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
	prometheusRegistry.config()
		.commonTags("app", "ratpack")
		.commonTags("env", "dev")
		.commonTags("appVersion", "1");
	this.bindMetrics(prometheusRegistry);
	return prometheusRegistry;
    }

    private void bindMetrics(final PrometheusMeterRegistry prometheusRegistry) {
	try (final JvmGcMetrics jvmGcMetrics = new JvmGcMetrics()) {
	    jvmGcMetrics.bindTo(prometheusRegistry);
	}
	new JvmMemoryMetrics().bindTo(prometheusRegistry);
	new JvmThreadMetrics().bindTo(prometheusRegistry);
	new ClassLoaderMetrics().bindTo(prometheusRegistry);
	new DiskSpaceMetrics(this.getDiskSpaceMetricsFile()).bindTo(prometheusRegistry);
	new UptimeMetrics().bindTo(prometheusRegistry);
	new FileDescriptorMetrics().bindTo(prometheusRegistry);
	new ProcessorMetrics().bindTo(prometheusRegistry);
    }

    private File getDiskSpaceMetricsFile() {
	this.createFileOnDisk();
	return new File(MICROMETER_DISK_METRICS);
    }

    private void createFileOnDisk() {
	try {
	    Files.write(
		    Paths.get(MICROMETER_DISK_METRICS),
		    "metrics".getBytes(),
		    StandardOpenOption.CREATE,
		    StandardOpenOption.TRUNCATE_EXISTING
	    );
	} catch (final IOException e) {
	    this.logger.warn("[METRICS] Could not write metrics file", e);
	}
    }
}
