package org.kesler.mfc.routeforms.server.repository.support;

import org.kesler.mfc.routeforms.server.domain.Auto;
import org.kesler.mfc.routeforms.server.domain.Employee;
import org.kesler.mfc.routeforms.server.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

/**
 * JPA репозиторий для сотрудников
 *
 */
@Repository
public class JpaEmployeeRepositoryImpl implements EmployeeRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Employee> findEmployees() throws DataAccessException {
        log.info("Reading all Employees...");
        TypedQuery<Employee> query = em.createQuery("SELECT DISTINCT employee FROM Employee employee", Employee.class);
        List<Employee> employees = query.getResultList();
        log.info("Read " + employees.size() + " employees");
        return employees;
    }

    @Override
    public void save(Employee employee) throws DataAccessException {
        log.info("Saving Employee: " + employee.getFio());
        if (employee.getId() == null) {
            log.debug("Persisting..");
            em.persist(employee);
        } else {
            log.debug("Merging..");
            em.merge(employee);
        }
        log.info("Saving complete");
    }

    @Override
    public void remove(UUID id) throws DataAccessException {
        log.info("Removing Employee: " + id);
        Employee managedEmployee = em.getReference(Employee.class, id);
        em.remove(managedEmployee);
        log.info("Removing complete");
    }
}
