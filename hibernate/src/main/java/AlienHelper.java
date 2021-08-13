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

        Alien alien = new Alien();

        alien.setColor(color);
        alien.setId(id);
        alien.setName(name);

        Transaction tx = session.beginTransaction();
        session.save(alien);
        tx.commit();
    }
    public static Alien getAlien(Long id){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Alien.class);

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
