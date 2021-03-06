import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Alien {
    @Id
    @Column(name = "id", nullable = false)
    private Long alienId;
    @Embedded
    private AlienName name;
    private String color;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alien")
    private List<AlienWeapon> weapons = new ArrayList<>();

    public Alien() {

    }

    public Alien(Long id, AlienName name, String color) {
        this.alienId = id;
        this.name = name;
        this.color = color;
    }

    public void addWeapon(AlienWeapon weapon) {
        weapons.add(weapon);
        weapon.setAlien(this);
    }

    public void removeWeapon(AlienWeapon weapon) {
        weapons.remove(weapon);
        weapon.setAlien(null);
    }

    public List<AlienWeapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<AlienWeapon> weapons) {
        for (AlienWeapon weapon : weapons) {
            addWeapon(weapon);
        }
    }

    public Long getAlienId() {
        return alienId;
    }

    public void setAlienId(Long alienId) {
        this.alienId = alienId;
    }


    public AlienName getName() {
        return name;
    }

    public void setName(AlienName name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "alienId=" + alienId +
                ", name=" + name +
                ", color='" + color + '\'' +
                ", weapons=" + weapons +
                '}';
    }
}
