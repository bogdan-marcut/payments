package com.bogdan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author bogdan.marcut 18/01/2021.
 */
@Getter
@AllArgsConstructor
@Builder
@ToString
public class Employee {

    private final Long id;
    private final String title;
    private final String name;

}
