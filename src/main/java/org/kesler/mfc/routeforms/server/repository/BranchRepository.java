package org.kesler.mfc.routeforms.server.repository;

import org.kesler.mfc.routeforms.server.domain.Branch;
import org.kesler.mfc.routeforms.server.domain.BranchRouteFormEditing;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для Филиала
 */
public interface BranchRepository {
    List<Branch> findBranches() throws DataAccessException;
    Branch findBranchById(UUID id) throws DataAccessException;
    void save(Branch branch) throws DataAccessException;
    void remove(UUID id) throws DataAccessException;
    BranchRouteFormEditing checkRouteFormEditing(UUID branchId);
    void setRouteFormEditing(UUID branchId);
    void unsetRouteFormEditing(UUID branchId);
}
