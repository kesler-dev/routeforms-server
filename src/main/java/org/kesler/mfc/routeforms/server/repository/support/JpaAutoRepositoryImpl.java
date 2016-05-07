package org.kesler.mfc.routeforms.server.repository.support;

import org.kesler.mfc.routeforms.server.domain.Auto;
import org.kesler.mfc.routeforms.server.domain.Branch;
import org.kesler.mfc.routeforms.server.repository.AutoRepository;
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
 * JPA репозиторий для автомобилей
 */
@Repository
public class JpaAutoRepositoryImpl implements AutoRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Auto> findAutos() throws DataAccessException {
        log.info("Reading all Autos..");
        TypedQuery<Auto> query = em.createQuery("SELECT DISTINCT auto FROM Auto auto", Auto.class);
        List<Auto> autos = query.getResultList();
        log.info("Read " + autos.size() + " Autos");
        return autos;
    }

    @Override
    public List<Auto> findAutosByBranch(Branch branch) throws DataAccessException {
        log.info("Reading Autos by Branch: " + branch);
        TypedQuery<Auto> query = em.createQuery("SELECT DISTINCT auto FROM Auto auto WHERE auto.branch=:branch", Auto.class);
        query.setParameter("branch", branch);
        List<Auto> autos = query.getResultList();
        log.info("Read " + autos.size() + " Autos");
        return autos;
    }

    @Override
    public Auto findAutoById(UUID id) throws DataAccessException {
        log.info("Reading Auto by id: " + id);
        TypedQuery<Auto> query = em.createQuery("SELECT a FROM Auto a WHERE a.id=:id", Auto.class);
        query.setParameter("id", id);
        Auto auto = query.getSingleResult();
        log.info("Read Auto " + auto);
        return auto;
    }

    @Override
    public void save(Auto auto) throws DataAccessException {
        log.info("Saving Auto: " + auto.toString());
        if (auto.getId() == null) {
            log.debug("Persisting..");
            em.persist(auto);
        } else {
            log.debug("Merging..");
            em.merge(auto);
        }
        log.info("Saving complete");
    }

    @Override
    public void remove(UUID id) throws DataAccessException {
        log.info("Removing Auto: " + id);
        Auto managedAuto = em.getReference(Auto.class, id);
        em.remove(managedAuto);
        log.info("Removing complete");
    }
}
