package dao;

import model.City;

import javax.persistence.EntityManager;

public class CityDAOImpl implements CityDAO {
    private final EntityManager em;

    public CityDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addCity(City city) {
        em.getTransaction().begin();
        em.persist(city);
        em.getTransaction().commit();
    }

    @Override
    public void removeCity(City city) {
        em.getTransaction().begin();
        try {
            em.remove(city);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } finally {
            em.getTransaction().commit();
        }
    }

    @Override
    public void removeCityByID(Integer id) {
        removeCity(getCityByID(id));
    }

    @Override
    public void renameCity(City city, String newName) {
        em.getTransaction().begin();
        city.setCityName(newName);
        em.getTransaction().commit();
    }

    @Override
    public City getCityByID(Integer id) {
        return em.find(City.class, id);
    }
}
