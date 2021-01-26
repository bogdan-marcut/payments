package com.bogdan.employee.repository;

import static com.bogdan.jooq.Tables.EMPLOYEE;

import java.util.function.Function;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ratpack.http.ClientErrorException;

import com.bogdan.employee.model.EmployeeDto;

/**
 * @author bogdan.marcut 18/01/2021.
 */
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final DSLContext create;

    @Inject
    public EmployeeRepositoryImpl(final DataSource connection) {
	this.create = DSL.using(connection, SQLDialect.MYSQL);
    }

    @Override
    public EmployeeDto findById(final Long id) {
	final Result<Record> result = this.create.select()
		.from(EMPLOYEE)
		.where(EMPLOYEE.ID.eq(id.intValue()))
		.fetch();

	return result.stream()
		.findFirst()
		.map(this.mapRecordToEmployeeDto())
		.orElseThrow(this::notFoundException);
    }

    private Function<Record, EmployeeDto> mapRecordToEmployeeDto() {
	return record -> EmployeeDto.builder()
		.id(Long.valueOf(record.get(EMPLOYEE.ID)))
		.name(record.get(EMPLOYEE.NAME))
		.title(record.get(EMPLOYEE.TITLE))
		.build();
    }

    @Override
    public Long addEmployee(final EmployeeDto employeeDto) {
	final int insertedId = this.create.insertInto(EMPLOYEE,
		EMPLOYEE.ID, EMPLOYEE.NAME, EMPLOYEE.TITLE)
		.values(null, employeeDto.getName(), employeeDto.getTitle())
		.returningResult(EMPLOYEE.ID)
		.execute();

	return (long) insertedId;
    }

    private ClientErrorException notFoundException() {
	return new ClientErrorException("Id is not informed") {
	    @Override
	    public int getClientErrorCode() {
		return 400;
	    }
	};
    }

}
