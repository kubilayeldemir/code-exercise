import javax.persistence.*;
import java.util.List;

@Entity
public class Alien {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Embedded
    private AlienName name;
    private String color;
    @OneToOne
    private AlienWeapon weapon;

    public Alien() {

    }

    public Alien(Long id, AlienName name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public AlienWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(AlienWeapon weapon) {
        this.weapon = weapon;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", name=" + name +
                ", color='" + color + '\'' +
                '}';
    }
}
