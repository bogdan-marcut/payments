package com.bogdan.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Bogdan Marcut 19/01/2021.
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
