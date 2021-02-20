package com.bogdan.metrics.service;

import java.util.Map;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MediaType;
import ratpack.http.internal.HttpHeaderConstants;

import com.google.inject.Inject;

/**
 * @author bogdan.marcut 20/02/2021.
 */
public class MetricsChain implements Action<Chain> {

    private static final String URL = "url";
    private static final String PATH = "path";
    private static final String METHOD = "method";
    private static final String RESPONSE = "response";

    private final PrometheusMeterRegistry registry;

    @Inject
    public MetricsChain(final PrometheusMeterRegistry registry) {
	this.registry = registry;
    }

    @Override
    public void execute(final Chain chain) throws Exception {
	chain.all(this.buildHttpMetrics());
	chain.prefix("metrics", this.defineMetricsApi());
    }

    private Action<Chain> defineMetricsApi() {
	return chain -> chain
		.get("prometheus", this.getPrometheusMetrics());
    }

    private Handler getPrometheusMetrics() {
	return ctx -> ctx
		.header(HttpHeaderConstants.CONTENT_TYPE, MediaType.PLAIN_TEXT_UTF8)
		.getResponse()
		.send(this.registry.scrape());
    }

    private Handler buildHttpMetrics() {
	return ctx -> {
	    this.countResponses(ctx);
	    this.measureResponses(ctx);
	    ctx.next();
	};
    }

    private void countResponses(final Context ctx) {
	ctx.getResponse().beforeSend(response -> Counter.builder("http.server.response.status.count")
		.baseUnit("beans")
		.description("http response counter by endpoint")
		.tag(URL, ctx.getRequest().getHeaders().get(HttpHeaderConstants.HOST))
		.tag(PATH, this.buildPath(ctx))
		.tag(METHOD, ctx.getRequest().getMethod().getName())
		.tag(RESPONSE, String.valueOf(response.getStatus()))
		.register(this.registry)
		.increment());
    }

    private void measureResponses(final Context ctx) {
	final Timer.Sample sample = Timer.start();

	ctx.getResponse().beforeSend(response -> {
	    final Timer timer = Timer.builder("http.server.response.time")
		    .tag(URL, ctx.getRequest().getHeaders().get(HttpHeaderConstants.HOST))
		    .tag(PATH, this.buildPath(ctx))
		    .tag(METHOD, ctx.getRequest().getMethod().getName())
		    .tag(RESPONSE, String.valueOf(response.getStatus()))
		    .publishPercentileHistogram()
		    .register(this.registry);
	    sample.stop(timer);
	});
    }

    private String buildPath(final Context ctx) {
	String requestPath = ctx.getRequest().getPath();
	for (final Map.Entry<String, String> entry : ctx.getPathBinding().getAllTokens().entrySet()) {
	    final String key = "/:" + entry.getKey();
	    final String value = "/" + entry.getValue();
	    requestPath = requestPath.replace(value, key);
	}
	return requestPath;
    }

}
