/*
 * This file is generated by jOOQ.
 */
package com.bogdan.jooq;


import com.bogdan.jooq.tables.Employee;
import com.bogdan.jooq.tables.records.EmployeeRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * payments.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<EmployeeRecord> KEY_EMPLOYEE_PRIMARY = Internal.createUniqueKey(Employee.EMPLOYEE, DSL.name("KEY_employee_PRIMARY"), new TableField[] { Employee.EMPLOYEE.ID }, true);
}
