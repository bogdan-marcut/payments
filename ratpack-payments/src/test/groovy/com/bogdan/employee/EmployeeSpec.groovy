package com.bogdan.employee

import com.bogdan.Application
import groovy.json.JsonSlurper
import ratpack.test.MainClassApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.Specification

/**
 * @author Bogdan Marcut 25/01/2021.
 */
class EmployeeSpec extends Specification {

    def aut = new MainClassApplicationUnderTest(Application)
    @Delegate
    TestHttpClient client = testHttpClient(aut)

    def "should get an employee by id"() {
        given:
        def employeeId = 1L

        when:
        get("/api/employee/" + employeeId)

        then:
        response.statusCode == 200
        def actualEmployee = new JsonSlurper().parseText(response.body.text)
        actualEmployee.id == employeeId
    }

}
