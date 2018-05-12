package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Recipe")
public class Recept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String mealType;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })

    private List<Hozzavalo> ingredients = new ArrayList<>();

    public Recept() {
    }

    public Recept(String name, String description, String mealType) {
        this.name = name;
        this.description = description;
        this.mealType = mealType;
    }

    public void addHozzavalo(Hozzavalo hozzavalo){
        ingredients.add(hozzavalo);
        hozzavalo.getRecipes().add(this);
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

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public List<Hozzavalo> getHozzavalok() {
        return ingredients;
    }

    public void setHozzavalok(List<Hozzavalo> hozzavalok) {
        this.ingredients = hozzavalok;
    }

    @Override
    public String toString() {
        return "Recept: " +
                "  név: " + name +
                "  leírás: " + description +
                "  hozzávalók: " + ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recept)) return false;
        return id != null && id.equals(((Recept) o).id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, ingredients);
    }
}
