import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class hibernate {
    static public void main(String[] args) {
//        AlienHelper.saveAlien(1L,new AlienName("Kubilay","Kubi","Eldemir"),"Blue");
//        var alien = AlienHelper.getAlien(3L);
//        System.out.println(alien.toString());

        var myAlien = new Alien(23L,new AlienName("Kubilay","Kubi","Eldemir"),"Blue");
        var weapon = new AlienWeapon();
        weapon.setName("Destroyer");
        weapon.setId(2L);
        myAlien.setWeapon(weapon);

        AlienHelper.saveAlienAndWeapon(myAlien,weapon);
    }
}
