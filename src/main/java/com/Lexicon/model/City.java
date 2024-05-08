package com.Lexicon.model;


public class City {
    private int id;
    private String name;
    private String CountryCode;
    private String District;
    private int population;

    public City(int id, String name, String countryCode, String district, int population) {
        this.name = name;
        CountryCode = countryCode;
        District = district;
        this.population = population;
    }

    public City(int id, String name, String countryCode, int population) {
        this.id = id;
        this.name = name;
        CountryCode = countryCode;
        String district;
        //District = district;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", District='" + District + '\'' +
                ", population=" + population +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String updatedCity) {
    }
}
