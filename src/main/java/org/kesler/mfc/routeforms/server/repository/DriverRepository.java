package org.kesler.mfc.routeforms.server.repository;

import org.kesler.mfc.routeforms.server.domain.Branch;
import org.kesler.mfc.routeforms.server.domain.Driver;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для Водителя
 */
public interface DriverRepository {
    List<Driver> findDrivers() throws DataAccessException;
    List<Driver> findActiveDriversByBranch(Branch branch) throws DataAccessException;
    void save(Driver driver) throws DataAccessException;
    void remove(UUID id) throws DataAccessException;
}
