/*
 * This file is generated by jOOQ.
 */
package com.bogdan.jooq.routines;


import com.bogdan.jooq.Payments;

import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Insertrand extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>payments.InsertRand.NumRows</code>.
     */
    public static final Parameter<Integer> NUMROWS = Internal.createParameter("NumRows", SQLDataType.INTEGER, false, false);

    /**
     * Create a new routine call instance
     */
    public Insertrand() {
        super("InsertRand", Payments.PAYMENTS);

        addInParameter(NUMROWS);
    }

    /**
     * Set the <code>NumRows</code> parameter IN value to the routine
     */
    public void setNumrows(Integer value) {
        setValue(NUMROWS, value);
    }
}