package com.bogdan.employee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author bogdan.marcut 24/01/2021.
 */
@Getter
@AllArgsConstructor
@Builder
@ToString
public class EmployeeDto {

    private final Long id;
    private final String name;
    private final String title;

}
