package br.com.encurtadorurl.repository.impl;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.encurtadorurl.config.DatabaseConfig;
import br.com.encurtadorurl.domain.URL;
import br.com.encurtadorurl.repository.UrlRepository;

@Stateless
public class UrlRepositoryImpl implements UrlRepository {

    @PersistenceContext(unitName = "encurtadorURLPU")
    private EntityManager entityManager;

	@Override
	public URL save(URL url) {
		entityManager.persist(url);
		return url;
	}

	@Override
	public Optional<URL> findByURLEncurtada(String urlEncurtada) {
        TypedQuery<URL> query = entityManager.createQuery(
                "SELECT s FROM URL s WHERE s.urlEncurtada = :urlEncurtada", URL.class);
        query.setParameter("urlEncurtada", urlEncurtada);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

	@Override
	public long contarTotalURLs() {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(s) FROM URL s", Long.class);
        return query.getSingleResult();
	}

	@Override
	public long proximoValorSequence() {
        Number value = (Number) entityManager
                .createNativeQuery("SELECT NEXT VALUE FOR " + DatabaseConfig.URL_ENCURTADA_SEQUENCE_NAME)
                .getSingleResult();
        return value.longValue();
	}

}
