package org.kesler.mfc.routeforms.server.repository;

import org.kesler.mfc.routeforms.server.domain.Auto;
import org.kesler.mfc.routeforms.server.domain.Branch;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для Автомобиля
 */
public interface AutoRepository {
    List<Auto> findAutos() throws DataAccessException;
    List<Auto> findAutosByBranch(Branch branch) throws DataAccessException;
    Auto findAutoById(UUID id) throws DataAccessException;
    void save(Auto auto) throws DataAccessException;
    void remove(UUID id) throws DataAccessException;
}
