package com.bogdan;

import java.util.ArrayList;
import java.util.List;

import ratpack.server.RatpackServer;

import com.bogdan.model.Employee;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public class Application {

    public static void main(String[] args) throws Exception {
	RatpackServer.start(server -> server.handlers(chain -> chain
		.get(ctx -> ctx.render("Hello world!!!"))));
    }

}
