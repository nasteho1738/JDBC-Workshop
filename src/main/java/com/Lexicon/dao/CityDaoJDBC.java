package com.Lexicon.dao;

import com.Lexicon.db.SQLConnection;
import com.Lexicon.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao{
    private Connection connection;

    public CityDaoJDBC() {
        this.connection = connection;
    }

    @Override
    public City findById(int id) {
        City city = null;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement("select * from city where id =?")) {
                preparedStatement.setInt(1, id);
              try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                  if (resultSet.next()) {
                      city = new City(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("countryCode"), resultSet.getString("District"), resultSet.getInt("population"));
                  }
              }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("City: " + city);
        return city;
    }

    @Override
    public List<City> findByCode(String code) {
        List<City> cities = new ArrayList<>();
        City city = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from cities where countryCode =?")) {
            preparedStatement.setString(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    city.setId(resultSet.getInt("id"));
                    city.setName(resultSet.getString("name"));
                    city.setCountryCode(resultSet.getString("countryCode"));
                    city.setDistrict(resultSet.getString("District"));
                    city.setPopulation(resultSet.getInt("population"));
                    cities.add(city);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cities: " + cities);
        }
        return cities;
    }

    @Override
    public List<City> findByName(String name) {
        List<City> cities = new ArrayList<>();
        City city= null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from city where name like ?")) {
            preparedStatement.setString(1, "%" + name + "%");
            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    city.setId(resultSet.getInt("id"));
                    city.setName(resultSet.getString("name"));
                    city.setCountryCode(resultSet.getString("countryCode"));
                    city.setDistrict(resultSet.getString("District"));
                    city.setPopulation(resultSet.getInt("population"));
                    cities.add(city);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Cities: " + cities);
        return cities;
    }
    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        City city = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from city")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    city.setId(resultSet.getInt("id"));
                    city.setName(resultSet.getString("name"));
                    city.setCountryCode(resultSet.getString("countryCode"));
                    city.setDistrict(resultSet.getString("District"));
                    city.setPopulation(resultSet.getInt("population"));
                    cities.add(city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Cities: " + cities);
        return cities;
    }

    @Override
    public City add(City city) {
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("Insert into city (name, countryCode, district, population) values(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());


            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                city.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("City: " + city);
        return city;
    }

    @Override
    public int update(City city) {
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("Update city Set name =?, countryCode =?, district =?, population =? Where id =?")) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getId());

            rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0 ) {
                return rowsAffected;
            } else {
                throw new RuntimeException("Failed to update city");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Updated rows: " + rowsAffected);
        return rowsAffected;
    }

    @Override
    public int delete(City city) {
        int rowsAffected = 0;
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from city where id =?")) {
            preparedStatement.setInt(1, city.getId());
            rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Deleted rows: " + rowsAffected);
        return rowsAffected;
    }
}
