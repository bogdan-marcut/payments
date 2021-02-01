package com.bogdan.employee.service

import com.bogdan.employee.model.EmployeeDto
import com.bogdan.employee.repository.EmployeeRepository
import spock.lang.Specification

/**
 * @author Bogdan Marcut 25/01/2021.
 */
class EmployeeChainTest extends Specification {

    EmployeeChain service
    EmployeeRepository repository = Mock(EmployeeRepository)

    def setup() {
        service = new EmployeeChain(repository)
    }

    def "should get an employee"() {
        given:
        def expectedEmployee = EmployeeDto.builder()
                .id(1)
                .name("Mock name")
                .title("Mr")
                .build()
        repository.findById(1) >> expectedEmployee

        when:
        def actualEmployee = repository.findById(1)

        then:
        actualEmployee == expectedEmployee
    }

    def "should add an employee"() {
        given:
        def expectedEmployeeToAdd = EmployeeDto.builder()
                .id(1)
                .name("Mock name")
                .title("Mr")
                .build()
        repository.addEmployee(expectedEmployeeToAdd) >> 1

        when:
        def actualAddedEmployeeId = repository.addEmployee(expectedEmployeeToAdd)

        then:
        actualAddedEmployeeId == expectedEmployeeToAdd.id
    }
    
}
