package service;

import dao.HozzavaloDao;
import dao.ReceptDAO;
import impl.HozzavaloImpl;
import model.Hozzavalo;
import model.Recept;

import java.util.ArrayList;
import java.util.List;

public class ReceptServiceImpl implements ReceptService{

    private ReceptDAO dao;
    private HozzavaloDao hozzavaloDao;

    public ReceptServiceImpl(ReceptDAO dao) {
        this.dao = dao;
    }

    public ReceptServiceImpl(HozzavaloDao hozzavaloDao) {
        this.hozzavaloDao = hozzavaloDao;
    }

    @Override
    public void createHozzavaloAddToRecept(List<Hozzavalo> hozzavaloList, Recept recept) {
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
    public void createRecept(Recept recept) {
        dao.persist(recept);
    }

    @Override
    public List<Recept> searchRecept(List<String> hozzavaloList) {
        return dao.searchRecept(hozzavaloList);
    }

    @Override
    public List<Recept> searchFilteredRecept(List<String> hozzavaloList) {
        return dao.searchFilteredRecept(hozzavaloList);
    }

    @Override
    public List<Recept> getAllRecept() {
        return dao.getAllRecept();
    }
}
