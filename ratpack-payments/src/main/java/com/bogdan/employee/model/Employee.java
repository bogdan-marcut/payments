package com.bogdan.employee.model;

import java.io.Serializable;

import javax.annotation.concurrent.Immutable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author bogdan.marcut 18/01/2021.
 */
@Immutable
@JsonDeserialize
public class Employee implements Serializable {

    public final Long id;
    public final String title;
    public final String name;

    public Employee(Long id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }
}
