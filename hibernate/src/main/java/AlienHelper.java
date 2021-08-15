import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AlienHelper {
    public static void saveAlien(Alien alien){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Alien.class).addAnnotatedClass(AlienWeapon.class);

        ServiceRegistry reg  = new ServiceRegistryBuilder().
                applySettings(configuration.getProperties()).buildServiceRegistry();

        SessionFactory sf = configuration.buildSessionFactory(reg);

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();
        session.save(alien);
        tx.commit();
    }
    public static Alien getAlien(Long id){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Alien.class).addAnnotatedClass(AlienWeapon.class);

        ServiceRegistry reg  = new ServiceRegistryBuilder().
                applySettings(configuration.getProperties()).buildServiceRegistry();

        SessionFactory sf = configuration.buildSessionFactory(reg);

        Session session = sf.openSession();

        Alien alien;
        //Stackoverflow: Without transaction you can only retrieve object from database
        alien = (Alien) session.get(Alien.class,id);
        return alien;
    }
}
