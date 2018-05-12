package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Ingredients")
public class Hozzavalo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private List<Recept> recipes = new ArrayList<>();

    private String type;

    public Hozzavalo() {
    }

    public Hozzavalo( String name, String type) {
        this.name = name;
        this.type = type;
    }

    public List<Recept> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recept> recipes) {
        this.recipes = recipes;
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
        return type;
    }

    public void setTipus(String type) {
        this.type = type;
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

        return Objects.hash(recipes);
    }
}
