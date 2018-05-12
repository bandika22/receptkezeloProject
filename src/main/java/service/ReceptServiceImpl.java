package service;

import dao.HozzavaloDao;
import dao.RecipeDAO;
import model.Hozzavalo;
import model.Recept;

import java.util.List;

public class ReceptServiceImpl implements ReceptService{

    private RecipeDAO dao;

    public ReceptServiceImpl(RecipeDAO dao) {
        this.dao = dao;
    }

    @Override
    public void createIngredientsAddToRecipe(List<Hozzavalo> hozzavaloList, Recept recept) {
        /*List<Hozzavalo> createdIngredients = new ArrayList<>();
        for (int i = 0; i < hozzavaloList.size(); i++) {
            createdIngredients.add(hozzavaloList.get(i));
            hozzavaloList.get(i).getRecepts().add(recept);
        }*/
        for (int i = 0; i < hozzavaloList.size(); i++) {
            recept.addHozzavalo(hozzavaloList.get(i));
        }
        /*List<Hozzavalo> exIt = new ArrayList<>();
        List<Hozzavalo> notexIt = new ArrayList<>();


       // System.out.println(hozzavaloDao.isExists(new Hozzavalo("Liszt")));
           *//* if (e) {
                *//**//*exIt.add(hvalo.get(i));
                recept.setHozzavalok(exIt);*//**//*
                System.out.println("exist");
            } else {
                System.out.println("notexist");
                *//**//*notexIt.add(hvalo.get(i));
                recept.setHozzavalok(notexIt);
                hvalo.get(i).getRecepts().add(recept);*//**//*
            }*//*
        }*/


    }

    @Override
    public void createRecipe(Recept recept) {
        dao.persist(recept);
    }

    @Override
    public List<Recept> searchRecipe(List<String> hozzavaloList) {
        return dao.searchRecept(hozzavaloList);
    }

    @Override
    public List<Recept> searchFilteredRecipe(List<String> mealTypeList) {
        return dao.searchFilteredRecipe(mealTypeList);
    }

    @Override
    public List<Recept> searchContainedRecipe(List<String> ingredientsTypeList) {
        return dao.searchContainedRecipe(ingredientsTypeList);
    }

    @Override
    public List<Recept> getAllRecept() {
        return dao.getAllRecept();
    }
}
