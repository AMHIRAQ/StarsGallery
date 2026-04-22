package com.example.starsgallery.service;

import com.example.starsgallery.beans.Star;
import com.example.starsgallery.dao.IDao;
import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {
    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        stars = new ArrayList<>();
        seed();
    }

    public static StarService getInstance() {
        if (instance == null) instance = new StarService();
        return instance;
    }

    private void seed() {
        stars.add(new Star("Kate Bosworth", "kate", 3.2f));
        stars.add(new Star("George Clooney", "george", 3.5f));
        stars.add(new Star("Michelle Rodriguez", "michelle", 4.5f));
        stars.add(new Star("Scarlett Johansson", "scarlett", 4.8f));
    }

    @Override public boolean create(Star o) { return stars.add(o); }

    @Override public boolean update(Star o) {
        for (Star s : stars) {
            if (s.getId() == o.getId()) {
                s.setName(o.getName());
                s.setImg(o.getImg());
                s.setStar(o.getStar());
                return true;
            }
        }
        return false;
    }

    @Override public boolean delete(Star o) { return stars.remove(o); }

    @Override public Star findById(int id) {
        for (Star s : stars) if (s.getId() == id) return s;
        return null;
    }

    @Override public List<Star> findAll() { return stars; }
}