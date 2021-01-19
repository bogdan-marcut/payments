package com.bogdan;

import com.bogdan.employee.EmployeeModule;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public class Application {

    public static void main(String[] args) throws Exception {
	final EmployeeModule employeeModule = new EmployeeModule();

	Server server = new Server(employeeModule.createService());
	server.start();

    }

}
