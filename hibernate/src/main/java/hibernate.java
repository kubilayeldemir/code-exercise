import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class hibernate {
    static public void main(String[] args) {
        System.out.println("merhaba");
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Alien.class);

        ServiceRegistry reg  = new ServiceRegistryBuilder().
                applySettings(configuration.getProperties()).buildServiceRegistry();

        SessionFactory sf = configuration.buildSessionFactory(reg);


        Session session = sf.openSession();

        Alien ali = new Alien();
        ali.setColor("Green");
        ali.setId(1L);
        ali.setName("Ali");
        Transaction tx = session.beginTransaction();
        session.save(ali);
        tx.commit();
    }
}
