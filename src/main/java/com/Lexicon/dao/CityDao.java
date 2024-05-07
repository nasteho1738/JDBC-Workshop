package com.Lexicon;

public interface CityDao {
    City findById(int id);
    List<City> findByCode(String code);
    List<City> findByName(String name);
    LIst<City> findAll();
    City add(City city);
    City update(City city);
    int delete(City city);

}
