package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Hozzavalo")
public class Hozzavalo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "hozzavalok1")
    private List<Recept> recepts = new ArrayList<>();

    private String tipus;

    public Hozzavalo() {
    }

    public Hozzavalo( String name, String tipus) {
        this.name = name;
        this.tipus = tipus;
    }


    public List<Recept> getRecepts() {
        return recepts;
    }

    public void setRecepts(List<Recept> recepts) {
        this.recepts = recepts;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    @Override
    public String toString() {
        return "- "+ name + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hozzavalo hozzavalo = (Hozzavalo) o;
        return Objects.equals(name, hozzavalo.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(recepts);
    }
}
