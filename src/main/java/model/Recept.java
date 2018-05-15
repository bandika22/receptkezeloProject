package model;

import controller.UploadController;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(name = "Recept")
public class Recept {

    protected static final org.slf4j.Logger log = LoggerFactory.getLogger(Recept.class);

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

    /**
     * Konstruktor, amely reprezentálja a recept objektumot.
     * @param name A recept neve.
     * @param description A recept leírása
     * @param mealType A recept típusa.
     */
    public Recept(String name, String description, String mealType) {
        this.name = name;
        this.description = description;
        this.mealType = mealType;
        log.info("Create a " + name +" recipe.");
    }

    public Recept(String name, String description, String mealType, List<Hozzavalo> ingredients) {
        this.name = name;
        this.description = description;
        this.mealType = mealType;
        this.ingredients = ingredients;
    }

    /**
     * Hozzáadja a létrehozott recepthez a hozzávalót.
     * @param ingredients hozzávaló, melyet hozzáad a létrehozott recepthez.
     */
    public void addIngredients(Hozzavalo ingredients){
        this.ingredients.add(ingredients);
        ingredients.getRecipes().add(this);
        log.info("Add a(n) "+ ingredients.getName() + " ingredient to " + this.name + " recipe");
    }

    /**
     * A recept egyedi azonosítójával tér vissza
     * @return A recept egyedi azonosítója.
     */
    public long getId() {
        return id;
    }

    /**
     * Beállítja a recept egyedi azonosítóját
     * @param id a recept egyedi azonosítója
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Visszaadja a recept nevét.
     * @return A recept neve.
     */

    public String getName() {
        return name;
    }

    /**
     * Beállítja a recept nevét.
     * @param name A recept neve.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Visszaadja a recept leírását.
     * @return A recept leírása.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Beállítja a recept leírását.
     * @param description A recept leírása.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Visszaadja a recept típusát.
     * @return A recept típusa.
     */
    public String getMealType() {
        return mealType;
    }

    /**
     * Beállítja a recept típusát.
     * @param mealType A recept típusa.
     */
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    /**
     * Visszaadja a recepthez tartozó hozzávalók listáját.
     * @return A recepthez tartozó hozzávalók listája.
     */
    public List<Hozzavalo> getIngredients() {
        return ingredients;
    }

    /**
     * Beállítja a recepthez tartozó hozzávalók listáját.
     * @param hozzavalok A recepthez tartozó hozzávalók listája.
     */
    public void setIngredients(List<Hozzavalo> hozzavalok) {
        this.ingredients = hozzavalok;
    }

    /**
     * Visszaadja a recept objectum attribútumait Stringként.
     * @return String, ami a recept objectum attribútumait tartalmazza.
     */
    @Override
    public String toString() {
        return "Recept: " +
                "  név: " + name +
                "  leírás: " + description +
                "  étel típusa: " + mealType +
                "  hozzávalók: " + ingredients;
    }

    /**
     * Összehasonlít két recept objectumot és igazzal tér vissza, ha ezek megegyeznek.
     * @param o Recept objectum.
     * @return Igaz, ha a két objectum megegyezik, egyébként hamis.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recept recept = (Recept) o;
        return Objects.equals(name, recept.name) &&
                Objects.equals(description, recept.description) &&
                Objects.equals(mealType, recept.mealType) &&
                Objects.equals(ingredients, recept.ingredients);
    }

}
