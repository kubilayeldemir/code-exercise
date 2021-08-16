import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AlienHelper {
    public static void saveAlien(Alien alien) {
        SessionFactory sf = PersistentService.getSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();
        session.save(alien);
        tx.commit();
    }

    public static void saveWeapon(AlienWeapon weapon) {
        SessionFactory sf = PersistentService.getSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();
        session.save(weapon);
        tx.commit();
    }

    public static void saveAlienTitle(AlienTitle alienTitle) {
        SessionFactory sf = PersistentService.getSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();
        session.save(alienTitle);
        tx.commit();
    }

    public static Alien getAlien(Long id) {
        SessionFactory sf = PersistentService.getSessionFactory();
        Session session = sf.openSession();

        Alien alien;
        //Stackoverflow: Without transaction you can only retrieve object from database
        alien = session.get(Alien.class, id);
        return alien;
    }

    public static List<AlienTitle> getTitles(Long alienId) {
        SessionFactory sf = PersistentService.getSessionFactory();
        Session session = sf.openSession();

        String query = "select ali " +
                " from AlienTitle ali" +
                " where ali.alien.id = :alienId";
        var q = session.createQuery(query);
        q.setParameter("alienId", alienId);

        return (List<AlienTitle>) q.list();
    }
}
