package org.kesler.mfc.routeforms.server.repository.support;

import org.kesler.mfc.routeforms.server.domain.Branch;
import org.kesler.mfc.routeforms.server.domain.BranchRouteFormEditing;
import org.kesler.mfc.routeforms.server.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

/**
 * JPA репозиторий для клиентов
 */
@Repository
public class JpaBranchRepositoryImpl implements BranchRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Branch> findBranches() throws DataAccessException {
        log.info("Reading all Branches..");
        TypedQuery<Branch> query = em.createQuery("SELECT DISTINCT branch FROM Branch branch", Branch.class);
        List<Branch> branches = query.getResultList();
        log.info("Read " + branches.size() + " Branches");
        return branches;
    }

    @Override
    public Branch findBranchById(UUID id) {
        log.info("Reading Branch by id: " + id);
        TypedQuery<Branch> query = em.createQuery("SELECT b FROM Branch b WHERE b.id=:id", Branch.class);
        query.setParameter("id", id);
        Branch branch = query.getSingleResult();
        return branch;
    }

    @Override
    public void save(Branch branch) throws DataAccessException {
        log.info("Saving Branch: " + branch.getName());
        if (branch.getId() == null) {
            log.debug("Persisting..");
            em.persist(branch);
        } else {
            log.debug("Merging..");
            em.merge(branch);
        }
        log.info("Saving complete");
    }

    @Override
    public void remove(UUID id) throws DataAccessException {
        log.info("Removing Branch: " + id);
        Branch managedBranch = em.getReference(Branch.class, id);
        em.remove(managedBranch);
        log.info("Removing complete");
    }

    @Override
    public BranchRouteFormEditing checkRouteFormEditing(UUID branchId) {
        log.info("Checking routeForm is editing for branch: " + branchId);
        BranchRouteFormEditing editing = em.find(BranchRouteFormEditing.class, branchId);
        log.info("Check result: " + editing);
        return editing;
    }

    @Override
    public void setRouteFormEditing(BranchRouteFormEditing editing) {
        log.info("Setting RouteForm editing for branch: " + editing.getId());
        em.merge(editing);
        log.info("Setting complete");
    }

    @Override
    public void unsetRouteFormEditing(UUID branchId) {
        log.info("Unsetting RouteForm editing for branch: " + branchId);
        BranchRouteFormEditing editing = em.find(BranchRouteFormEditing.class, branchId);
        if (editing!=null) {
            em.remove(editing);
        } else {
            log.debug("Not found RouteForm editing");
        }
        log.info("Unsetting complete");
    }
}
