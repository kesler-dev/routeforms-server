package org.kesler.mfc.routeforms.server.repository;

import org.kesler.mfc.routeforms.server.domain.Norm;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для норм
 */
public interface NormRepository {
    List<Norm> findNorms() throws DataAccessException;
    void save(Norm norm) throws DataAccessException;
    void remove(UUID id) throws DataAccessException;
}
