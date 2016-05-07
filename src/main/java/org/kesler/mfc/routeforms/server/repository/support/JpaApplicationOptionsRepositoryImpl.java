package org.kesler.mfc.routeforms.server.repository.support;

import org.kesler.mfc.routeforms.server.domain.ApplicationOptions;
import org.kesler.mfc.routeforms.server.repository.ApplicationOptionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * JPA репозиторий для настроек
 */
@Repository
public class JpaApplicationOptionsRepositoryImpl implements ApplicationOptionsRepository {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public ApplicationOptions load() throws DataAccessException {
        log.info("Reading Options..");
        Query query = em.createQuery("SELECT options FROM ApplicationOptions options");
        List<ApplicationOptions> applicationOptionsList =  query.getResultList();

        ApplicationOptions applicationOptions = null;
        if (applicationOptionsList.size() > 0) {
            log.info("Read success: " + applicationOptionsList.size());
            applicationOptions = applicationOptionsList.get(0);
        } else {
            log.info("Read unsuccess");
        }
        return applicationOptions;
    }

    @Override
    public void save(ApplicationOptions applicationOptions) throws DataAccessException {
        log.info("Saving Options...");
        if (applicationOptions.getId() == null) {
            log.debug("Persisting..");
            em.persist(applicationOptions);
        } else {
            log.debug("Merging..");
            em.merge(applicationOptions);
        }
        log.info("Saving complete");
    }


}
