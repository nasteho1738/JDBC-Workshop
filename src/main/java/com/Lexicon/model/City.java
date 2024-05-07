package com.Lexicon.model;


public class City {
    private int id;
    private String name;
    private String CountryCode;
    private String District;
    private int population;

    public City(String name, String countryCode, String district, int population) {
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

    public String getDistrict() {
        return District;
    }

    public int getPopulation() {
        return population;
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
