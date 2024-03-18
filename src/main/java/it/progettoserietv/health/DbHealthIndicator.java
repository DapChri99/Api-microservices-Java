package it.progettoserietv.health;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class DbHealthIndicator implements HealthIndicator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Health health() {
        try {
            //Query al db per vedere se è up
//            entityManager.createNativeQuery("SELECT name FROM tbl_tv_show WHERE name = 'Golden Girls'").getSingleResult();
            entityManager.createNativeQuery("SELECT 1 FROM tbl_tv_show").getSingleResult();
            // Costruisco uno stato di salute UP
            return Health.up().build();

        } catch (Exception e) {
            // Verifico se l'eccezione è dovuta a un problema di connessione al database
            if (e instanceof PersistenceException || e.getCause() instanceof SQLException) {
                // Se l'eccezione è dovuta a un problema di connessione al database, restituisco stato DOWN
                return Health.down().withException(e).build();
            } else {
                // Altrimenti, restituisco uno stato UNKNOWN per indicare un problema non specificato
                return Health.unknown().withException(e).build();
            }
        }
    }
}
