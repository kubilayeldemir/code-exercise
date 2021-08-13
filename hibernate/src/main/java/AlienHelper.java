import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AlienHelper {
    public static void saveAlien(Long id, AlienName name, String color){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Alien.class);

        ServiceRegistry reg  = new ServiceRegistryBuilder().
                applySettings(configuration.getProperties()).buildServiceRegistry();

        SessionFactory sf = configuration.buildSessionFactory(reg);

        Session session = sf.openSession();

        Alien ali = new Alien();

        ali.setColor(color);
        ali.setId(id);
        ali.setName(name);

        Transaction tx = session.beginTransaction();
        session.save(ali);
        tx.commit();
    }
}
