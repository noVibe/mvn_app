package dao;

import model.City;

public interface CityDAO {
    void addCity(City city);
    void removeCity(City city);
    void removeCityByID(Integer id);
    void renameCity(City city, String newName);

    City getCityByID(Integer id);
}
