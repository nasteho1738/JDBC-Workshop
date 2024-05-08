package com.Lexicon;

import com.Lexicon.dao.CityDaoJDBC;
import com.Lexicon.model.City;

import java.sql.Connection;
import java.util.List;

public class App {

        public static void main(String[] args) {
                CityDaoJDBC cityDaoJDBC = new CityDaoJDBC();
                System.out.println("find city with id 1: ");
                City cityid = cityDaoJDBC.findById(1);
                System.out.println(cityid);



        }
}
