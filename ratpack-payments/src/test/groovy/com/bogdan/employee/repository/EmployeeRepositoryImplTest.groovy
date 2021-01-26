package com.bogdan.employee.repository

import com.bogdan.employee.model.Employee
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

/**
 * @author Bogdan Marcut 25/01/2021.
 */
class EmployeeRepositoryImplTest extends Specification {

    EmployeeRepositoryImpl repository

    def setup(){
        repository = new EmployeeRepositoryImpl()
    }

    def "should add an employee"() {
        given:
        def expectedEmployeeToAdd = Employee.builder()
                .id(1)
                .name("Mock name")
                .title("Mr")
                .build()

        when:
        def actualAddedEmployee = repository.addEmployee(expectedEmployeeToAdd)

        then:
        actualAddedEmployee == expectedEmployeeToAdd
    }

}
