import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import java.math.BigInteger;
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
    public static List<Alien> getAliensNative(Long alienId) {
        SessionFactory sf = PersistentService.getSessionFactory();
        Session session = sf.openSession();

        NativeQuery query = session.createNativeQuery("select * from alien ");
        query.addEntity(Alien.class);
        List<Alien> aliens = query.list();

        return  aliens;
    }
    public static Integer getAlienWeaponNumber(Long alienId) {
        SessionFactory sf = PersistentService.getSessionFactory();
        Session session = sf.openSession();

        NativeQuery query = session.createNativeQuery("select count(*) from alienweapon where alien_id=:alienId ");
        query.setParameter("alienId",alienId);
        List<Object> weaponNumberList = query.list();

        return ((BigInteger) weaponNumberList.get(0)).intValue();
    }
}