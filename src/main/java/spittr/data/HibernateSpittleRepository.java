package spittr.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spittr.Spittle;

import java.io.Serializable;
import java.util.List;

/**
 * @author arc3102
 * @date 2021/3/1 13:52
 */
@Repository
public class HibernateSpittleRepository implements SpittleRepository{

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateSpittleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public List<Spittle> findSpittles(long max, int count) {
        return (List<Spittle>) currentSession()
                .createCriteria(Spittle.class)
                .list();
    }

    @Override
    @Transactional
    public Spittle findOne(long spittleId) {
        List<Spittle> spittles = currentSession()
                .createCriteria(Spittle.class)
                .add(Restrictions.eq("id", spittleId))
                .list();
        if (spittles.isEmpty()) {
            return null;
        }
        return spittles.get(0);
    }

    @Override
    @Transactional
    public Spittle saveSpittle(Spittle spittle) {
        Session session = currentSession();
        Serializable id = currentSession().save(spittle);
        return new Spittle((Long) id,
                spittle.getMessage(),
                spittle.getTime(),
                spittle.getLatitude(),
                spittle.getLongitude());
    }

}
