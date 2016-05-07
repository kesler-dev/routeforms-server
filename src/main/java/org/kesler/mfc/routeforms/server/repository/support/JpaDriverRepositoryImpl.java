package org.kesler.mfc.routeforms.server.repository.support;

import org.kesler.mfc.routeforms.server.domain.Branch;
import org.kesler.mfc.routeforms.server.domain.Driver;
import org.kesler.mfc.routeforms.server.repository.DriverRepository;
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
 * JPA репозиторий для водителей
 */
@Repository
public class JpaDriverRepositoryImpl implements DriverRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Driver> findDrivers() throws DataAccessException {
        log.info("Reading all Drivers..");
        TypedQuery<Driver> query = em.createQuery("SELECT DISTINCT driver FROM Driver driver", Driver.class);
        List<Driver> drivers = query.getResultList();
        log.info("Read " + drivers.size() + " Drivers");
        return drivers;
    }

    @Override
    public List<Driver> findActiveDriversByBranch(Branch branch) throws DataAccessException {
        log.info("Reading Drivers by Branch " + branch);
        TypedQuery<Driver> query = em.createQuery("SELECT DISTINCT driver FROM Driver driver WHERE driver.branch=:branch " +
                "AND driver.active=true", Driver.class);
        query.setParameter("branch", branch);
        List<Driver> drivers = query.getResultList();
        log.info("Read " + drivers.size() + " Drivers");
        return drivers;
    }

    @Override
    public void save(Driver driver) throws DataAccessException {
        log.info("Saving Driver: " + driver.getFioShort());
        if (driver.getId() == null) {
            log.debug("Persisting..");
            em.persist(driver);
        } else {
            log.debug("Merging..");
            em.merge(driver);
        }
        log.info("Saving complete");
    }

    @Override
    public void remove(UUID id) throws DataAccessException {
        log.info("Removing Driver: " + id);
        Driver managedDriver = em.getReference(Driver.class, id);
        em.remove(managedDriver);
        log.info("Removing complete");
    }
}
