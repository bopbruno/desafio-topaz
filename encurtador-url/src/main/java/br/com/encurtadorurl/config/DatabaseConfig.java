package br.com.encurtadorurl.config;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class DatabaseConfig {

    public static final String URL_ENCURTADA_SEQUENCE_NAME = "url_encurtada_seq";

    @PersistenceContext(unitName = "encurtadorURLPU")
    private EntityManager entityManager;

    @PostConstruct
    public void criarURLEncurtadaSequence() {
        // DDL idempotente - seguro de rodar a cada startup/redeploy, mesmo
        // que a sequence ja exista.
        entityManager.createNativeQuery(
                        "CREATE SEQUENCE IF NOT EXISTS " + URL_ENCURTADA_SEQUENCE_NAME + " START WITH 1 INCREMENT BY 1")
                .executeUpdate();
    }

}
