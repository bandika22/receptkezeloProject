package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Recept")
public class Recept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "recept_hozzavalo",
            joinColumns = @JoinColumn(name = "recept_id"),
            inverseJoinColumns = @JoinColumn(name = "hozzavalo_id")
    )
    private List<Hozzavalo> hozzavalok1 = new ArrayList<>();

    public Recept() {
    }

    public Recept(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addHozzavalo(Hozzavalo hozzavalo){
        hozzavalok1.add(hozzavalo);
        hozzavalo.getRecepts().add(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nev) {
        this.name = nev;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String leiras) {
        this.description = leiras;
    }

    public List<Hozzavalo> getHozzavalok() {
        return hozzavalok1;
    }

    public void setHozzavalok(List<Hozzavalo> hozzavalok) {
        this.hozzavalok1 = hozzavalok;
    }

    @Override
    public String toString() {
        return "Recept: " +
                "  név: " + name +
                "  leírás: " + description +
                "  hozzávalók: " + hozzavalok1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recept)) return false;
        return id != null && id.equals(((Recept) o).id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, hozzavalok1);
    }
}
