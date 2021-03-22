package spittr.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spittr.Spitter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * @author arc3102
 * @date 2021/2/20 23:13
 */
//@Repository
@Transactional
public class JpaSpitterRepository implements SpitterRepository{

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Spitter save(Spitter spitter) {
        entityManager.persist(spitter);
        return spitter;
    }

    @Override
    public Spitter findByUsername(String username) {
        return (Spitter) emf.createEntityManager().createQuery("select s from Spitter s where s.username=?").setParameter(1, username).getSingleResult();
    }
}
