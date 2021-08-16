import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PersistentService {
    private static SessionFactory sessionFactory = null;

    private PersistentService() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = getConfig();
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    private static Configuration getConfig() {
        return new Configuration().configure().addAnnotatedClass(Alien.class).addAnnotatedClass(AlienWeapon.class).addAnnotatedClass(AlienTitle.class);
    }
}
