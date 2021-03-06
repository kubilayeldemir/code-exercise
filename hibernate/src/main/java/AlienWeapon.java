import javax.persistence.*;

@Entity
public class AlienWeapon {
    @Id
    private Long weaponId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "alien_id")
    private Alien alien;

    public AlienWeapon() {
    }

    public AlienWeapon(Long weaponId, String name) {
        this.weaponId = weaponId;
        this.name = name;
    }

    public Alien getAlien() {
        return alien;
    }

    public void setAlien(Alien alien) {
        this.alien = alien;
    }

    public Long getId() {
        return weaponId;
    }

    public void setId(Long id) {
        this.weaponId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AlienWeapon{" +
                "weaponId=" + weaponId +
                ", name='" + name + '\'' +
                '}';
    }
}
