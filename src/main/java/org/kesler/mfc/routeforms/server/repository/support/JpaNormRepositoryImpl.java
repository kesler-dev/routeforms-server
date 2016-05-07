package org.kesler.mfc.routeforms.server.repository.support;

import org.kesler.mfc.routeforms.server.domain.Norm;
import org.kesler.mfc.routeforms.server.repository.NormRepository;
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
 * JPA репозиторий для норм
 */
@Repository
public class JpaNormRepositoryImpl implements NormRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Norm> findNorms() throws DataAccessException {
        log.info("Reading all Norms..");
        TypedQuery<Norm> query = em.createQuery("SELECT DISTINCT norm FROM Norm norm", Norm.class);
        List<Norm> norms = query.getResultList();
        log.info("Read " + norms.size() + " Norms");
        return norms;
    }

    @Override
    public void save(Norm norm) throws DataAccessException {
        log.info("Saving Norm: " + norm.toString());
        if (norm.getId() == null) {
            log.debug("Persisting..");
            em.persist(norm);
        } else {
            log.debug("Merging..");
            em.merge(norm);
        }
        log.info("Saving complete");
    }

    @Override
    public void remove(UUID id) throws DataAccessException {
        log.info("Removing Norm: " + id);
        Norm managedNorm = em.getReference(Norm.class, id);
        em.remove(managedNorm);
        log.info("Removing complete");
    }
}
