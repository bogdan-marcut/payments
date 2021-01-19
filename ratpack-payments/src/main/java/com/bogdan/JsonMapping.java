package com.bogdan;

import java.util.concurrent.CompletionStage;

import ratpack.exec.Promise;
import ratpack.jackson.Jackson;
import ratpack.jackson.JsonRender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/**
 * @author Bogdan Marcut 18/01/2021.
 */
public class JsonMapping {

    private static final ObjectMapper MAPPER = configureMapping();

    private static ObjectMapper configureMapping() {
	return new ObjectMapper()
		.registerModule(new ParameterNamesModule())
		.registerModule(new Jdk8Module())
		.registerModule(new JavaTimeModule());
    }


    public static ObjectMapper getJsonMapping() {
	return JsonMapping.MAPPER;
    }

    public static Promise<JsonRender> toJsonPromise(CompletionStage<?> future) {
	return Promise.async(
		d -> d.accept(future.thenApply(Jackson::json))
	);
    }

}
