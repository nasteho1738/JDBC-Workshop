package com.Lexicon.dao;

import com.Lexicon.db.SQLConnection;
import com.Lexicon.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao{


    @Override
    public City findById(int id) {
        City city = null;

        try (
                Connection connection = SQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from city where id =?")) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                city = new City(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("countryCode"), resultSet.getInt("District"));
            }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public List<City> findByCode(String code) {
        List<City> cities = new ArrayList<>();
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from cities where countryCode =?")) {
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cities.add(new City(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("countryCode"), resultSet.getInt("District")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findByName(String name) {
        List<City> cities = new ArrayList<>();
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from cities where name like?")) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cities.add(new City(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("countryCode"), resultSet.getInt("District")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from cities")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cities.add(new City(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("countryCode"), resultSet.getInt("District")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public City add(City city) {
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("Insert into cities(name, countryCode, district) values(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());

            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                city.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public City update(City city) {
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("Update cities Set name =?, countryCode =?, District =? Where id =?")) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0 ) {
                return city;
            } else {
                throw new RuntimeException("Failed to update city");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(City city) {
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from cities where id =?")) {
            preparedStatement.setInt(1, city.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
