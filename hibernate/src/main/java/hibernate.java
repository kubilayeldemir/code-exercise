import java.util.ArrayList;
import java.util.List;

public class hibernate {
    static public void main(String[] args) {
//        AlienHelper.saveAlien(1L,new AlienName("Kubilay","Kubi","Eldemir"),"Blue");
//        var alien = AlienHelper.getAlien(3L);
//        System.out.println(alien.toString());

        var myAlien = new Alien(23L,new AlienName("Kubilay","Kubi","Eldemir"),"Blue");
        List<AlienWeapon> weaponList = new ArrayList<>();

        weaponList.add(new AlienWeapon(2L,"KDP",myAlien));
        weaponList.add(new AlienWeapon(3L,"Full Moon",myAlien));


        myAlien.setWeapons(weaponList);

        AlienHelper.saveAlien(myAlien);

    }
}
