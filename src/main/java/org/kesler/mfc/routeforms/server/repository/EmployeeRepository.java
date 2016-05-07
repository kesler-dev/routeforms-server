package org.kesler.mfc.routeforms.server.repository;

import org.kesler.mfc.routeforms.server.domain.Employee;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для сотрудников
 */
public interface EmployeeRepository {
    List<Employee> findEmployees() throws DataAccessException;
    void save(Employee employee) throws DataAccessException;
    void remove(UUID id) throws DataAccessException;
}
