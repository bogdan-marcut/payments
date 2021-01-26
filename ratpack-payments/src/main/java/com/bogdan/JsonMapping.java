package com.bogdan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/**
 * @author Bogdan Marcut 18/01/2021.
 */
public class JsonMapping {

    private static final ObjectMapper MAPPER = configureMapping();

    private JsonMapping() {
	throw new IllegalStateException();
    }

    private static ObjectMapper configureMapping() {
	return new ObjectMapper()
		.registerModule(new ParameterNamesModule())
		.registerModule(new Jdk8Module())
		.registerModule(new JavaTimeModule());
    }

    public static ObjectMapper getJsonMapping() {
	return JsonMapping.MAPPER;
    }

    public static String toJson(final Object object) throws JsonProcessingException {
	return JsonMapping.MAPPER.writeValueAsString(object);
    }

    public static <T> T toObject(final String json, Class<T> toValueType) {
        return JsonMapping.MAPPER.convertValue(json, toValueType);
    }

}
