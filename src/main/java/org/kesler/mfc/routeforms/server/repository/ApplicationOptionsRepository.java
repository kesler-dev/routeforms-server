package org.kesler.mfc.routeforms.server.repository;

import org.kesler.mfc.routeforms.server.domain.ApplicationOptions;
import org.springframework.dao.DataAccessException;

/**
 * Репозиторий для Водителя
 */
public interface ApplicationOptionsRepository {
    ApplicationOptions load() throws DataAccessException;
    void save(ApplicationOptions applicationOptions) throws DataAccessException;
}
