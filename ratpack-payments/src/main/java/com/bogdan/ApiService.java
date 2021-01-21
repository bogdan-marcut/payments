package com.bogdan;

import java.util.concurrent.CompletionStage;

import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.handling.Context;

/**
 * @author bogdan.marcut 21/01/2021.
 */
public interface ApiService {

    Action<Chain> buildServiceApi();

    default void renderResponse(final Context ctx, final CompletionStage<?> object) {
	ctx.render(JsonMapping.toJsonPromise(object));
    }

}
