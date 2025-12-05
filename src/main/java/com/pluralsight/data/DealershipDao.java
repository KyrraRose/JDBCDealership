package com.pluralsight.data;
import com.pluralsight.model.Dealership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDao {

    private final DataManager dataManager;

    public DealershipDao(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Dealership> loadDealerships() {
        List<Dealership> dealerships = new ArrayList<>();
        String query = "SELECT * FROM dealerships";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet results = statement.executeQuery();) {

                    while (results.next()) {

                        dealerships.add(new Dealership(
                                results.getInt("dealership_id"),
                                results.getString("name"),
                                results.getString("address"),
                                results.getString("phone")
                        ));
                    }


            }

        } catch (SQLException e) {
            System.err.println("Error searching: " + e.getMessage());
            e.printStackTrace();
        }

        return dealerships;
    }

    public Dealership loadDealership(int input) {
        Dealership dealership = null;
        String query = "SELECT * FROM dealerships WHERE dealership_id = ?";

        try {
            Connection connection = dataManager.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query);){
                statement.setInt(1,input);
                try(ResultSet results = statement.executeQuery();) {

                    while (results.next()) {

                        dealership = new Dealership(
                                results.getInt("dealership_id"),
                                results.getString("name"),
                                results.getString("address"),
                                results.getString("phone")
                        );
                    }
                }

            }

        } catch (SQLException e) {
            System.err.println("Error searching: " + e.getMessage());
            e.printStackTrace();
        }

        return dealership;
    }

    public void addDealership(Dealership dealership){
        String insert = "INSERT INTO dealerships (dealership_id,name,address,phone) VALUES (?,?,?,?) ";
        try {
            Connection connection = dataManager.getConnection();

            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setInt(1,dealership.getDealershipId());
            statement.setString(2,dealership.getName());
            statement.setString(3,dealership.getAddress());
            statement.setString(4,dealership.getPhone());

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error searching for artists: " + e.getMessage());
            e.printStackTrace();
        }
    }

}