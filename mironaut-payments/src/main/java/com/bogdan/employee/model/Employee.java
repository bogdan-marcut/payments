package com.bogdan.employee.model;

/**
 * @author bogdan.marcut 24/01/2021.
 */
public class Employee {

    public final Long id;
    public final String name;
    public final String title;

    public Employee(final Long id, final String name, final String title) {
	this.id = id;
	this.name = name;
	this.title = title;
    }

}
