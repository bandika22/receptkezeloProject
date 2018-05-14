package model;

import controller.UploadController;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A recepthez tartozó hozzávalókat határozza meg.
 */
@Entity(name = "Hozzavalo")
public class Hozzavalo {

    protected static final org.slf4j.Logger log = LoggerFactory.getLogger(Hozzavalo.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private List<Recept> recipes = new ArrayList<>();

    private String type;

    public Hozzavalo() {
    }

    /**
     * Konsruktor, amely reprezentálja a hozzávaló objektumot.
     * @param name A hozzávaló neve.
     * @param type A hozzávaló típusa.
     */
    public Hozzavalo( String name, String type) {
        this.name = name;
        this.type = type;
        log.info("Create a(n) " + this.name + " ingredient");
    }

    /**
     * Visszaadja a hozzávalókhoz tartozó recepteket.
     * @return A hozzávalokhóz tartozó recept.
     */
    public List<Recept> getRecipes() {
        return recipes;
    }

    /**
     * Beállítja a hozzávalóhoz tartozó recepteket.
     * @param recipes A hozzávalóhoz tartozó receptek listája.
     */
    public void setRecipes(List<Recept> recipes) {
        this.recipes = recipes;
    }

    /**
     * Visszaadja a hozzávalóhoz tartozó egyedi azonosítót.
     * @return A hozzávaló egyedi azonosítója.
     */
    public long getId() {
        return id;
    }

    /**
     * Beállítja a hozzávaló egyedi azonosítóját.
     * @param id a hozzávaló egyedi azonosítója.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Visszaadja a hozzávalónak a nevét.
     * @return A hozzávaló neve.
     */
    public String getName() {
        return name;
    }

    /**
     * Beállítja a hozzávalónak a nevét.
     * @param name A hozzávaló neve.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Visszaadja a hozzávaló típusát.
     * @return A hozzávaló típusa.
     */
    public String getTipus() {
        return type;
    }

    /**
     * Beállítja a hozzávaló típusát.
     * @param type A hozzávaló típusa.
     */
    public void setTipus(String type) {
        this.type = type;
    }

    public void addRecipe(Recept recipe){
        this.recipes.add(recipe);
        recipe.getIngredients().add(this);
    }

    @Override
    public String toString() {
        return name;
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
